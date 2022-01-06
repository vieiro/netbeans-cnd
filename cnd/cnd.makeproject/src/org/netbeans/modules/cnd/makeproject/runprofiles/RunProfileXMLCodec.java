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

package org.netbeans.modules.cnd.makeproject.runprofiles;

import org.netbeans.modules.cnd.api.picklist.PicklistElement;
import org.netbeans.modules.cnd.makeproject.api.runprofiles.Env;
import org.netbeans.modules.cnd.makeproject.api.runprofiles.RunProfile;
import org.netbeans.modules.cnd.api.xml.AttrValuePair;
import org.netbeans.modules.cnd.api.xml.VersionException;
import org.netbeans.modules.cnd.api.xml.XMLDecoder;
import org.netbeans.modules.cnd.api.xml.XMLEncoder;
import org.netbeans.modules.cnd.api.xml.XMLEncoderStream;
import org.xml.sax.Attributes;

public class RunProfileXMLCodec extends XMLDecoder implements XMLEncoder {

    private final RunProfile profile;

    // was: public RunProfile.PROFILE_ID
    private static final String PROFILE_ID = "runprofile"; // NOI18N

    private final static String VARIABLE_ELEMENT = "variable"; // NOI18N
    private final static String NAME_ATTR = "name"; // NOI18N
    private final static String VALUE_ATTR = "value"; // NOI18N
    private final static String ENVIRONMENT_ELEMENT = "environment"; // NOI18N
    private final static String ARGS_ELEMENT = "args"; // NOI18N
    private final static String ARGUMENTS_ELEMENT = "arguments"; // NOI18N
    private final static String RUNDIR_ELEMENT = "rundir"; // NOI18N
    private final static String RUNCOMMAND_ELEMENT = "runcommand"; // NOI18N
    private final static String RUNCOMMAND_PICKLIST_ELEMENT = "runcommandpicklist"; // NOI18N
    private final static String RUNCOMMAND_PICKLIST_ITEM_ELEMENT = "runcommandpicklistitem"; // NOI18N
    private final static String BUILD_FIRST_ELEMENT = "buildfirst"; // NOI18N
    private final static String CONSOLE_TYPE_ELEMENT = "console-type"; // NOI18N
    private final static String TERMINAL_TYPE_ELEMENT = "terminal-type"; // NOI18N
    private final static String REMOVE_INSTRUMENTATION_ELEMENT = "remove-instrumentation"; // NOI18N

    public final static String TRUE_VALUE = "true"; // NOI18N
    public final static String FALSE_VALUE = "false"; // NOI18N


    /*
     *  V9 - NB 7.0
     *    Re-introduced Arguments (for dbxtool). It's hidden by default.
     *  V8 - NB 7.0
     *    Don't save ARGS_ELEMENT. Args and Run command are now merged.
        Versions changes tracker (started from version 6):
        2010/12/09
            Run Command field is added (see Bug 154529). Version is not upgraded, because default value the property is provided.
     */
    private final static int thisversion = 9;

    public RunProfileXMLCodec(RunProfile profile) {
	this.profile = profile;
    }

    public static int getVersion() {
	return thisversion;
    }

    // interface XMLDecoder
    @Override
    public String tag() {
	return PROFILE_ID;
    }

    // interface XMLDecoder
    @Override
    public void start(Attributes atts) throws VersionException {
        String what = "run profile"; // NOI18N
        int maxVersion = getVersion();
        checkVersion(atts, what, maxVersion);
    }

    // interface XMLDecoder
    @Override
    public void end() {
        profile.clearChanged();
    }

    // interface XMLDecoder
    @Override
    public void startElement(String element, Attributes atts) {
	if (element.equals(VARIABLE_ELEMENT)) {
	    profile.getEnvironment().
		putenv(atts.getValue(NAME_ATTR), atts.getValue(VALUE_ATTR));
	}
        else if(element.equals(RUNCOMMAND_PICKLIST_ELEMENT)) {
	}
    }

    // interface XMLDecoder
    @SuppressWarnings("deprecation")
    @Override
    public void endElement(String element, String currentText) {
	if (element.equals(ARGS_ELEMENT)) {
	    profile.setArgsRaw(currentText);
	}
	else if (element.equals(RUNDIR_ELEMENT)) {
	    profile.setRunDir(currentText);
	}
	else if (element.equals(ARGUMENTS_ELEMENT)) {
	    profile.getConfigurationArguments().setValue(currentText);
	}
	else if (element.equals(RUNCOMMAND_ELEMENT)) {
	    profile.getRunCommand().setValue(currentText); // FIXUP
            profile.getRunCommand().getPicklist().addElement(currentText);
	}
	else if (element.equals(BUILD_FIRST_ELEMENT)) {
	    profile.setBuildFirst(currentText.equals(TRUE_VALUE));
	}
	else if (element.equals(RUNCOMMAND_PICKLIST_ITEM_ELEMENT)) {
	    profile.getRunCommand().getPicklist().addElement(currentText);
	}
	else {
            int idx;            
            try {
                idx = Integer.parseInt(currentText);
            } catch (NumberFormatException ex) {
                idx = 0;
            }
            if (element.equals(CONSOLE_TYPE_ELEMENT)) {
                if (idx == RunProfile.CONSOLE_TYPE_DEFAULT) {
                    idx = RunProfile.getDefaultConsoleType();
                }
                profile.getConsoleType().setValue(idx);
            } else if (element.equals(TERMINAL_TYPE_ELEMENT)) {
                profile.getTerminalType().setValue(idx);
            } else if (element.equals(REMOVE_INSTRUMENTATION_ELEMENT)) {
                profile.getRemoveInstrumentation().setValue(idx);
            }
	}
    }


    /*
     * was: part of RunProfileHelper.java.writeEnvironmentBlock
     */

    private static void encode(XMLEncoderStream xes, String[] pair) {
	xes.element(VARIABLE_ELEMENT, 
		    new AttrValuePair[] {
			new AttrValuePair(NAME_ATTR, "" + pair[0]), // NOI18N
			new AttrValuePair(VALUE_ATTR, "" + pair[1]) // NOI18N
		    });
    }


    /*
     * was: RunProfileHelper.java.writeEnvironmentBlock
     */

    private static void encode(XMLEncoderStream xes, Env env) {
	String[][] environment = env.getenvAsPairs();
	xes.elementOpen(ENVIRONMENT_ELEMENT);
	for (int i = 0; i < environment.length; i++) {
	    encode(xes, environment[i]);
	}
	xes.elementClose(ENVIRONMENT_ELEMENT);
    }


    /*
     * was: RunProfileHelper.java.writeProfileBlock
     */

    @SuppressWarnings("deprecation")
    private static void encode(XMLEncoderStream xes, RunProfile profile) {
	xes.elementOpen(PROFILE_ID, getVersion());

        xes.elementOpen(RUNCOMMAND_PICKLIST_ELEMENT);
        PicklistElement[] elements = profile.getRunCommand().getPicklist().getElements();
        for (int i = (elements.length-1); i >= 0; i--) {
             xes.element(RUNCOMMAND_PICKLIST_ITEM_ELEMENT, elements[i].displayName());
        }
        xes.elementClose(RUNCOMMAND_PICKLIST_ELEMENT);

        xes.element(RUNCOMMAND_ELEMENT, profile.getRunCommand().getValue()); // FIXUP
        if (profile.getConfigurationArguments().getModified()) {
            xes.element(ARGUMENTS_ELEMENT, profile.getConfigurationArguments().getValue());
        }
	xes.element(RUNDIR_ELEMENT, profile.getRunDir());
	xes.element(BUILD_FIRST_ELEMENT, "" + profile.getBuildFirst()); // NOI18N
        if (profile.getConsoleType().getModified()) {
            xes.element(CONSOLE_TYPE_ELEMENT, Integer.toString(profile.getConsoleType().getValue()));
        }
        xes.element(TERMINAL_TYPE_ELEMENT, Integer.toString(profile.getTerminalType().getValue()));
        xes.element(REMOVE_INSTRUMENTATION_ELEMENT, Integer.toString(profile.getRemoveInstrumentation().getValue()));
	encode(xes, profile.getEnvironment());
	xes.elementClose(PROFILE_ID);
    }

    // interface XMLEncoder
    @Override
    public void encode(XMLEncoderStream xes) {
	encode(xes, profile);
    } 
}
