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
package org.apache.vysper.xmpp.protocol;

import static java.util.Objects.requireNonNull;

import org.apache.vysper.xmpp.delivery.StanzaRelay;
import org.apache.vysper.xmpp.server.ServerRuntimeContext;
import org.apache.vysper.xmpp.server.StanzaReceivingSessionContext;
import org.apache.vysper.xmpp.stanza.Stanza;

/**
 * @author Réda Housni Alaoui
 */
class SimpleStanzaHandlerExecutor implements StanzaHandlerExecutor {

    private final StanzaRelay stanzaRelay;

    private final StanzaHandler stanzaHandler;

    public SimpleStanzaHandlerExecutor(StanzaRelay stanzaRelay, StanzaHandler stanzaHandler) {
        this.stanzaRelay = requireNonNull(stanzaRelay);
        this.stanzaHandler = requireNonNull(stanzaHandler);
    }

    @Override
    public void execute(Stanza stanza, ServerRuntimeContext serverRuntimeContext, boolean isOutboundStanza,
                        StanzaReceivingSessionContext sessionContext, SessionStateHolder sessionStateHolder) throws ProtocolException {
        stanzaHandler.execute(stanza, serverRuntimeContext, isOutboundStanza, sessionContext, sessionStateHolder,
                new SimpleStanzaBroker(stanzaRelay, sessionContext));
    }

}
