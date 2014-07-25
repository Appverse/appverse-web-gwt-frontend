/*
 Copyright (c) 2012 GFT Appverse, S.L., Sociedad Unipersonal.

 This Source Code Form is subject to the terms of the Appverse Public License
 Version 2.0 (“APL v2.0”). If a copy of the APL was not distributed with this
 file, You can obtain one at http://www.appverse.mobi/licenses/apl_v2.0.pdf. [^]

 Redistribution and use in source and binary forms, with or without modification,
 are permitted provided that the conditions of the AppVerse Public License v2.0
 are met.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. EXCEPT IN CASE OF WILLFUL MISCONDUCT OR GROSS NEGLIGENCE, IN NO EVENT
 SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT(INCLUDING NEGLIGENCE OR OTHERWISE)
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.
 */
package org.appverse.web.framework.backend.frontfacade.rest.application;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.appverse.web.framework.backend.api.helpers.security.XSSSecurityFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Due to what seems a bug in Jersey 2.4 (already reported https://java.net/jira/browse/JERSEY-2174)
 * In order to make use of MultiPart in Jersey Resources we are forced to register it manually.
 * Therefore, we need to define a Jersey Application instead of using the package auto-discovery feature of resources in Jersey, and manually register the MultiPartFeature.class.
 *
 * Due to another Jersey bug https://java.net/jira/browse/JERSEY-2301
 * letting jersey scan resources invalidates AOP for those resources.
 * A workaround is to let Spring create the beans and register 'manually' those as jax-rs resources through ResourceConfig.
 *
 */
public class JerseyInitRestApplication extends ResourceConfig{
	@Inject
    public JerseyInitRestApplication(ServletContext servletContext) {
        super(XSSSecurityFilter.class, JacksonFeature.class);

    }
}
