/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.integration.beans;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.mina.filter.firewall.BlacklistFilter;

/**
 * Java Bean {@link java.beans.PropertyEditor} which converts Strings into
 * {@link InetAddress} objects. This may be used together with Spring to be able
 * to set {@link InetAddress} bean properties, e.g.
 * {@link BlacklistFilter#setBlacklist(InetAddress[])}.
 * Simply calls {@link InetAddress#getByName(java.lang.String)} when
 * converting from a String.
 * <p>
 * Use Spring's CustomEditorConfigurer to use this property editor in a Spring
 * configuration file. See chapter 3.14 of the Spring Reference Documentation
 * for more info.
 * </p>
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 * @version $Revision$, $Date$
 *
 * @see java.net.InetAddress
 */
public class InetAddressEditor extends AbstractPropertyEditor {
    @Override
    protected String toText(Object value) {
        return ((InetAddress) value).getHostAddress();
    }

    @Override
    protected Object toValue(String text) throws IllegalArgumentException {
        try {
            return InetAddress.getByName(text);
        } catch (UnknownHostException uhe) {
            IllegalArgumentException iae = new IllegalArgumentException();
            iae.initCause(uhe);
            throw iae;
        }
    }
}
