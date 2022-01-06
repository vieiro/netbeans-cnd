/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.mercurial.remote.ui.wizards;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.security.KeyManagementException;
import java.util.logging.Logger;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.JComponent;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;
import org.netbeans.modules.mercurial.remote.Mercurial;
import org.netbeans.modules.mercurial.remote.HgModuleConfig;
import org.netbeans.modules.mercurial.remote.ui.repository.HgURL;
import org.netbeans.modules.mercurial.remote.ui.repository.Repository;
import org.netbeans.modules.mercurial.remote.ui.repository.RepositoryConnection;
import static org.netbeans.modules.mercurial.remote.ui.repository.HgURL.Scheme.FILE;
import static org.netbeans.modules.mercurial.remote.ui.repository.HgURL.Scheme.HTTP;
import static org.netbeans.modules.mercurial.remote.ui.repository.HgURL.Scheme.HTTPS;
import static org.netbeans.modules.mercurial.remote.ui.repository.Repository.FLAG_SHOW_HINTS;
import static org.netbeans.modules.mercurial.remote.ui.repository.Repository.FLAG_SHOW_PROXY;
import static org.netbeans.modules.mercurial.remote.ui.repository.Repository.FLAG_URL_ENABLED;
import org.netbeans.modules.remotefs.versioning.api.VCSFileProxySupport;
import org.netbeans.modules.versioning.core.api.VCSFileProxy;
import org.openide.filesystems.FileSystem;

public class CloneRepositoryWizardPanel implements WizardDescriptor.AsynchronousValidatingPanel, ChangeListener {
    
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private JComponent component;
    private Repository repository;
    private boolean valid;
    private String errorMessage;
    private WizardStepProgressSupport support;
    private final VCSFileProxy root;

    public CloneRepositoryWizardPanel(VCSFileProxy root) {
        this.root = root;
        support = new RepositoryStepProgressSupport();
    }
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new JPanel(new BorderLayout());
            if (root != null) {
                repository = new Repository(
                        FLAG_URL_ENABLED | FLAG_SHOW_HINTS | FLAG_SHOW_PROXY,
                        getMessage("CTL_Repository_Location"),
                        false, root);
                repository.addChangeListener(this);

                support = new RepositoryStepProgressSupport();


                component.add(repository.getPanel(), BorderLayout.CENTER);
                component.add(support.getProgressComponent(), BorderLayout.SOUTH);
            }
            component.setName(getMessage("repositoryPanel.Name"));       //NOI18N
            valid();
        }
        return component;
    }
    
    @Override
    public HelpCtx getHelp() {
        return new HelpCtx(CloneRepositoryWizardPanel.class);
    }

    private static String getMessage(String msgKey) {
        return NbBundle.getMessage(CloneRepositoryWizardPanel.class, msgKey);
    }
    
    //public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
    //    return true;
        // If it depends on some condition (form filled out...), then:
        // return someCondition();
        // and when this condition changes (last form field filled in...) then:
        // fireChangeEvent();
        // and uncomment the complicated stuff below.
    //}
    
    @Override
    public void stateChanged(ChangeEvent evt) {
        if (root != null) {
            if(repository.isValid()) {
                valid(repository.getMessage());
            } else {
                invalid(repository.getMessage());
            }
        } else {
            invalid(NbBundle.getMessage(CloneRepositoryWizardPanel.class, "NO_CONNECTED_REMOTE_FS"));
        }
    }

    private final Set<ChangeListener> listeners = new HashSet<>(1); // or can use ChangeSupport in NB 6.0
    @Override
    public final void addChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }
    @Override
    public final void removeChangeListener(ChangeListener l) {
        synchronized (listeners) {
            listeners.remove(l);
        }
    }
    protected final void fireChangeEvent() {
        Iterator<ChangeListener> it;
        synchronized (listeners) {
            it = new HashSet<>(listeners).iterator();
        }
        ChangeEvent ev = new ChangeEvent(this);
        while (it.hasNext()) {
            it.next().stateChanged(ev);
        }
    }
    
    protected final void valid() {
        setValid(true, null);
    }

    protected final void valid(String extErrorMessage) {
        setValid(true, extErrorMessage);
    }

    protected final void invalid(String message) {
        setValid(false, message);
    }

    @Override
    public final boolean isValid() {
        return valid;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    private void displayErrorMessage(String errorMessage) {
        if (errorMessage == null) {
            throw new IllegalArgumentException("<null> message"); //NOI18N
        }

        if (!errorMessage.equals(this.errorMessage)) {
            this.errorMessage = errorMessage;
            fireChangeEvent();
        }
    }

    private void setValid(boolean valid, String errorMessage) {
        if ((errorMessage != null) && (errorMessage.length() == 0)) {
            errorMessage = null;
        }
        boolean fire = this.valid != valid;
        fire |= errorMessage != null && (errorMessage.equals(this.errorMessage) == false);
        this.valid = valid;
        this.errorMessage = errorMessage;
        if (fire) {
            fireChangeEvent();
        }
    }

    protected void validateBeforeNext() throws WizardValidationException {
        if (repository == null) {
            return;
        }
        try {
            HgURL url;
            try {
                url = repository.getUrl();
            } catch (URISyntaxException ex) {
                throw new WizardValidationException((JComponent) component,
                                                    ex.getMessage(),
                                                    ex.getLocalizedMessage());
            }

            if (support == null) {
                support = new RepositoryStepProgressSupport();
                component.add(support.getProgressComponent(), BorderLayout.SOUTH);
            }
            support.setRepositoryRoot(url);
            RequestProcessor rp = Mercurial.getInstance().getRequestProcessor(url);
            RequestProcessor.Task task = support.start(rp, url, NbBundle.getMessage(CloneRepositoryWizardPanel.class, "BK2012"));
            task.waitFinished();
        } finally {
            if (support != null) {      //see bug #167172
                /*
                 * We cannot reuse the progress component because
                 * org.netbeans.api.progress.ProgressHandle cannot be reused.
                 */
                component.remove(support.getProgressComponent());
                support = null;
            }
        }

    }

    // comes on next or finish
    @Override
    public final void validate () throws WizardValidationException {
        try {
        validateBeforeNext();
        if (isValid() == false || errorMessage != null) {
            throw new WizardValidationException (
                (javax.swing.JComponent) component,
                errorMessage,
                errorMessage
            );
        }
        } catch (WizardValidationException ex) {
            EventQueue.invokeLater(new Runnable () {
                @Override
                public void run() {
                    if (repository != null) {
                        repository.setEditable(true);
                    }
                }
            });
            throw ex;
        }
    }

    // You can use a settings object to keep track of state. Normally the
    // settings object will be the WizardDescriptor, so you can use
    // WizardDescriptor.getProperty & putProperty to store information entered
    // by the user.
    @Override
    public void readSettings(Object settings) {}
    @Override
    public void storeSettings(Object settings) {
        if (settings instanceof WizardDescriptor) {
            try {
                ((WizardDescriptor) settings).putProperty("repository", repository.getUrl()); // NOI18N
            } catch (URISyntaxException ex) {
                /*
                 * The panel's data may not be validated yet (bug #163078)
                 * so we cannot assume that the entered URL is valid - so
                 * we must catch the URISyntaxException.
                 */
                Logger.getLogger(getClass().getName()).throwing(
                                                        getClass().getName(),
                                                        "storeSettings",//NOI18N
                                                        ex);
            }
        }
    }

    @Override
    public void prepareValidation() {
        errorMessage = null;
        
        repository.setEditable(false);
    }

    private void storeHistory() {
        RepositoryConnection rc = getRepositoryConnection();
        if(rc != null) {
            HgModuleConfig.getDefault(root).insertRecentUrl(rc);
        }
    }

    private RepositoryConnection getRepositoryConnection() {
        try {
            return repository.getRepositoryConnection();
        } catch (Exception ex) {
            displayErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public void stop() {
        if(support != null) {
            support.cancel();
        }
    }

    private class RepositoryStepProgressSupport extends WizardStepProgressSupport {

        public RepositoryStepProgressSupport() {
            super();
        }

        @Override
        public void perform() {
            final RepositoryConnection rc = getRepositoryConnection();
            if (rc == null) {
                return;
            }
            String invalidMsg = null;
            HttpURLConnection con = null;
            try {
                HgURL hgUrl = getRepositoryRoot();

                HgURL.Scheme uriSch = hgUrl.getScheme();
                if (uriSch == FILE) {
                    VCSFileProxy f = HgURL.getFile(root, hgUrl);
                    if(!f.exists() || !VCSFileProxySupport.canRead(f)){
                        invalidMsg = getMessage("MSG_Progress_Clone_CannotAccess_Err"); //NOI18N
                        return;
                    }
                } else if ((uriSch == HTTP) || (uriSch == HTTPS)) {
                    URL url = hgUrl.toURL();
                    con = (HttpURLConnection) url.openConnection();
                    // Note: valid repository returns con.getContentLength() = -1
                    // so no way to reliably test if this url exists, without using hg
                    if (con != null) {
                        String userInfo = url.getUserInfo();
                        boolean bNoUserAndOrPasswordInURL = userInfo == null;
                        // If username or username:password is in the URL the con.getResponseCode() returns -1 and this check would fail
                        if (uriSch == HTTPS) {
                            setupHttpsConnection(con);
                        }
                        if (bNoUserAndOrPasswordInURL && con.getResponseCode() != HttpURLConnection.HTTP_OK){
                            invalidMsg = getMessage("MSG_Progress_Clone_CannotAccess_Err"); //NOI18N
                            con.disconnect();
                            return;
                        }else if (userInfo != null){
                            Mercurial.LOG.log(Level.FINE, 
                                "RepositoryStepProgressSupport.perform(): UserInfo - {0}", new Object[]{userInfo}); // NOI18N
                        }
                    }
                 }
            } catch (java.lang.IllegalArgumentException ex) {
                 Mercurial.LOG.log(Level.INFO, ex.getMessage(), ex);
                 invalidMsg = getMessage("MSG_Progress_Clone_InvalidURL_Err"); //NOI18N
                 return;
            } catch (IOException ex) {
                 Mercurial.LOG.log(Level.INFO, ex.getMessage(), ex);
                 invalidMsg = getMessage("MSG_Progress_Clone_CannotAccess_Err"); //NOI18N
                return;
            } catch (RuntimeException re) {
                Throwable t = re.getCause();
                if(t != null) {
                    invalidMsg = t.getLocalizedMessage();
                } else {
                    invalidMsg = re.getLocalizedMessage();
                }
                Mercurial.LOG.log(Level.INFO, invalidMsg, re);
                return;
            } finally {
                if(con != null) {
                    con.disconnect();
                }
                if(isCanceled()) {
                  displayErrorMessage(getMessage("CTL_Repository_Canceled")); //NOI18N
                } else if(invalidMsg == null) {
                  storeHistory();
                } else {
                  displayErrorMessage(invalidMsg);
                }
            }
        }

        @Override
        public void setEditable(boolean editable) {
            repository.setEditable(editable);
        }

        private void setupHttpsConnection(HttpURLConnection con) {
            X509TrustManager tm = new X509TrustManager() {
                 @Override
                 public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                     // do nothing
                 }
                 @Override
                 public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                     // do nothing
                 }
                 @Override
                 public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                 }
            };
            HostnameVerifier hnv = new HostnameVerifier() {
                 @Override
                 public boolean verify(String hostname, SSLSession session) {
                    return true;
                 }
            };
            try {
                SSLContext context = SSLContext.getInstance("SSLv3"); //NOI18N
                TrustManager[] trustManagerArray = { tm };
                context.init(null, trustManagerArray, null);
                HttpsURLConnection c = (HttpsURLConnection) con;
                c.setSSLSocketFactory(context.getSocketFactory());
                c.setHostnameVerifier(hnv);
            } catch (KeyManagementException ex) {
                 Mercurial.LOG.log(Level.INFO, ex.getMessage(), ex);
            } catch (NoSuchAlgorithmException ex) {
                 Mercurial.LOG.log(Level.INFO, ex.getMessage(), ex);
            }
        }
    };

}

