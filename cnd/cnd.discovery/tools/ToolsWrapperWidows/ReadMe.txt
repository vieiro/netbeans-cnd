#    Licensed to the Apache Software Foundation (ASF) under one
#    or more contributor license agreements.  See the NOTICE file
#    distributed with this work for additional information
#    regarding copyright ownership.  The ASF licenses this file
#    to you under the Apache License, Version 2.0 (the
#    "License"); you may not use this file except in compliance
#    with the License.  You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing,
#    software distributed under the License is distributed on an
#    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#    KIND, either express or implied.  See the License for the
#    specific language governing permissions and limitations
#    under the License.


Tool collection is a Cygwin with installed MinGW-64-32 tools.
I.e.:
- install Cygwin (or cygwin64).
- install additional packages mingw64-i686-gcc-core GCC for Win32 MinGW 64-32 (i686-w44-mingw32) toolchain (C) and
                              mingw64-i686-gcc-g++ GCC for Win32 MinGW 64-32 (i686-w44-mingw32) toolchain (C++)
In IDE:
- add Cygwin tool collection with name Cygwin-32
- change C and C++ tools on C:\cygwin64\bin\i686-w64-mingw32-gcc.exe and C:\cygwin64\bin\i686-w64-mingw32-g++.exe

