
// Description: Spring StartupListener for the JpaTest program

/*
 *	io.github.msobkow.CFInt
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFInt - Internet Essentials
 *	
 *	This file is part of Mark's Code Fractal CFInt.
 *	
 *	Mark's Code Fractal CFInt is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU Library General Public License,
 *	Version 3 or later.
 *	
 *	Mark's Code Fractal CFInt is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU Library General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFInt is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU Library General Public License
 *	along with Mark's Code Fractal CFInt.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes in order to
 *	tie it to proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 *	
 */

package io.github.msobkow.v3_1.cfint.cfintjpatest.spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.concurrent.atomic.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfint.cfint.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;
import io.github.msobkow.v3_1.cfint.cfint.jpa.*;

@Component
public class CFIntJpaTestStartupListener implements ApplicationContextAware {
    @Autowired
    // @Qualifier("TestCFSec")
    private CFSecJpaTestTestSchema testCFSec;

    @Autowired
    // @Qualifier("TestCFInt")
    private CFIntJpaTestTestSchema testCFInt;


	static final AtomicReference<ApplicationContext> arApplicationContext = new AtomicReference<>();

	@Override
	public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
		arApplicationContext.compareAndSet(arApplicationContext.get(), applicationContext);
	}

	public static ApplicationContext getApplicationContext() {
		return( arApplicationContext.get() );
	}


	@Autowired
	@Qualifier("cfsec31JpaHooksSchema")
	private CFSecJpaHooksSchema cfsecJpaHooksSchema;

	@Autowired
	@Qualifier("cfint31JpaHooksSchema")
	private CFIntJpaHooksSchema cfintJpaHooksSchema;

	@EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        System.err.println("CFIntJpaTest StartupListener tests beginning...");

		ICFSecSchema.getBackingCFSec().setApplicationContext(getApplicationContext());
		ICFIntSchema.getBackingCFInt().setApplicationContext(getApplicationContext());

		((CFSecJpaSchema)ICFSecSchema.getBackingCFSec()).setJpaHooksSchema(cfsecJpaHooksSchema);
		((CFIntJpaSchema)ICFIntSchema.getBackingCFInt()).setJpaHooksSchema(cfintJpaHooksSchema);


		ICFSecSchema.getBackingCFSec().wireTableTableInstances();
		ICFIntSchema.getBackingCFInt().wireTableTableInstances();

		ICFSecSchema.getBackingCFSec().bootstrapSchema();
		ICFIntSchema.getBackingCFInt().bootstrapSchema();

        System.err.println("Executing testCFSec.performTests()");
        try {
            String response = testCFSec.performTests(null);
            if (response != null) {
                System.err.println("CFSecJpaTestTestSchema.performTests() responded: " + response);
            }
            else {
                System.err.println("CFSecJpaTestTestSchema.performTests() did not return a response");
            }
        }
        catch (Throwable th) {
            System.err.println("testCFSec.performTests() threw " + th.getClass().getCanonicalName() + " - " + th.getMessage());
            th.printStackTrace(System.err);
        }

        System.err.println("Executing testCFInt.performTests()");
        try {
            String response = testCFInt.performTests(null);
            if (response != null) {
                System.err.println("CFIntJpaTestTestSchema.performTests() responded: " + response);
            }
            else {
                System.err.println("CFIntJpaTestTestSchema.performTests() did not return a response");
            }
        }
        catch (Throwable th) {
            System.err.println("testCFInt.performTests() threw " + th.getClass().getCanonicalName() + " - " + th.getMessage());
            th.printStackTrace(System.err);
        }

        System.err.println("CFIntJpaTest StartupListener tests complete.");
    }
}
