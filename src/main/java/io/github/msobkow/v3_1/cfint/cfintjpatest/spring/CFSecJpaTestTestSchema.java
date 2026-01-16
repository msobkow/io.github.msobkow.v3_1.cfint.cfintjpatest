
// Description: Spring JPA tests for CFSec for the JpaTest program

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

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import io.github.msobkow.v3_1.cflib.*;
import io.github.msobkow.v3_1.cflib.dbutil.*;
import io.github.msobkow.v3_1.cfsec.cfsec.*;
import io.github.msobkow.v3_1.cfsec.cfsec.jpa.*;

@Service("JpaTestCFSec")
public class CFSecJpaTestTestSchema {
    
    @Autowired
    @Qualifier("cfsec31EntityManagerFactory")
    private LocalContainerEntityManagerFactoryBean cFSec31EntityManagerFactory;
	@Autowired
	private CFSecJpaClusterService cFSecClusterService;

	@Autowired
	private CFSecJpaHostNodeService cFSecHostNodeService;

	@Autowired
	private CFSecJpaISOCcyService cFSecISOCcyService;

	@Autowired
	private CFSecJpaISOCtryService cFSecISOCtryService;

	@Autowired
	private CFSecJpaISOCtryCcyService cFSecISOCtryCcyService;

	@Autowired
	private CFSecJpaISOCtryLangService cFSecISOCtryLangService;

	@Autowired
	private CFSecJpaISOLangService cFSecISOLangService;

	@Autowired
	private CFSecJpaISOTZoneService cFSecISOTZoneService;

	@Autowired
	private CFSecJpaSecDeviceService cFSecSecDeviceService;

	@Autowired
	private CFSecJpaSecGroupService cFSecSecGroupService;

	@Autowired
	private CFSecJpaSecGrpIncService cFSecSecGrpIncService;

	@Autowired
	private CFSecJpaSecGrpMembService cFSecSecGrpMembService;

	@Autowired
	private CFSecJpaSecSessionService cFSecSecSessionService;

	@Autowired
	private CFSecJpaSecUserService cFSecSecUserService;

	@Autowired
	private CFSecJpaServiceService cFSecServiceService;

	@Autowired
	private CFSecJpaServiceTypeService cFSecServiceTypeService;

	@Autowired
	private CFSecJpaSysClusterService cFSecSysClusterService;

	@Autowired
	private CFSecJpaTenantService cFSecTenantService;

	@Autowired
	private CFSecJpaTSecGroupService cFSecTSecGroupService;

	@Autowired
	private CFSecJpaTSecGrpIncService cFSecTSecGrpIncService;

	@Autowired
	private CFSecJpaTSecGrpMembService cFSecTSecGrpMembService;

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = NoResultException.class, transactionManager = "cfsec31TransactionManager")
    // @PersistenceContext(unitName = "CFSec31PU")
    public String performTests(EntityManager em) {
		StringBuffer messages = new StringBuffer("Starting CFSec tests...\n");
		List<?> clusterResults = cFSecClusterService.findAll();
		if (clusterResults == null) {
			messages.append("Erroneously retrieved null for CFSecClusterService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + clusterResults.size() + " entities from CFSec.Cluster\n");
		}

		List<?> hostNodeResults = cFSecHostNodeService.findAll();
		if (hostNodeResults == null) {
			messages.append("Erroneously retrieved null for CFSecHostNodeService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + hostNodeResults.size() + " entities from CFSec.HostNode\n");
		}

		List<?> iSOCcyResults = cFSecISOCcyService.findAll();
		if (iSOCcyResults == null) {
			messages.append("Erroneously retrieved null for CFSecISOCcyService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + iSOCcyResults.size() + " entities from CFSec.ISOCcy\n");
		}

		List<?> iSOCtryResults = cFSecISOCtryService.findAll();
		if (iSOCtryResults == null) {
			messages.append("Erroneously retrieved null for CFSecISOCtryService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + iSOCtryResults.size() + " entities from CFSec.ISOCtry\n");
		}

		List<?> iSOCtryCcyResults = cFSecISOCtryCcyService.findAll();
		if (iSOCtryCcyResults == null) {
			messages.append("Erroneously retrieved null for CFSecISOCtryCcyService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + iSOCtryCcyResults.size() + " entities from CFSec.ISOCtryCcy\n");
		}

		List<?> iSOCtryLangResults = cFSecISOCtryLangService.findAll();
		if (iSOCtryLangResults == null) {
			messages.append("Erroneously retrieved null for CFSecISOCtryLangService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + iSOCtryLangResults.size() + " entities from CFSec.ISOCtryLang\n");
		}

		List<?> iSOLangResults = cFSecISOLangService.findAll();
		if (iSOLangResults == null) {
			messages.append("Erroneously retrieved null for CFSecISOLangService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + iSOLangResults.size() + " entities from CFSec.ISOLang\n");
		}

		List<?> iSOTZoneResults = cFSecISOTZoneService.findAll();
		if (iSOTZoneResults == null) {
			messages.append("Erroneously retrieved null for CFSecISOTZoneService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + iSOTZoneResults.size() + " entities from CFSec.ISOTZone\n");
		}

		List<?> secDeviceResults = cFSecSecDeviceService.findAll();
		if (secDeviceResults == null) {
			messages.append("Erroneously retrieved null for CFSecSecDeviceService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + secDeviceResults.size() + " entities from CFSec.SecDevice\n");
		}

		List<?> secGroupResults = cFSecSecGroupService.findAll();
		if (secGroupResults == null) {
			messages.append("Erroneously retrieved null for CFSecSecGroupService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + secGroupResults.size() + " entities from CFSec.SecGroup\n");
		}

		List<?> secGrpIncResults = cFSecSecGrpIncService.findAll();
		if (secGrpIncResults == null) {
			messages.append("Erroneously retrieved null for CFSecSecGrpIncService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + secGrpIncResults.size() + " entities from CFSec.SecGrpInc\n");
		}

		List<?> secGrpMembResults = cFSecSecGrpMembService.findAll();
		if (secGrpMembResults == null) {
			messages.append("Erroneously retrieved null for CFSecSecGrpMembService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + secGrpMembResults.size() + " entities from CFSec.SecGrpMemb\n");
		}

		List<?> secSessionResults = cFSecSecSessionService.findAll();
		if (secSessionResults == null) {
			messages.append("Erroneously retrieved null for CFSecSecSessionService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + secSessionResults.size() + " entities from CFSec.SecSession\n");
		}

		List<?> secUserResults = cFSecSecUserService.findAll();
		if (secUserResults == null) {
			messages.append("Erroneously retrieved null for CFSecSecUserService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + secUserResults.size() + " entities from CFSec.SecUser\n");
		}

		List<?> serviceResults = cFSecServiceService.findAll();
		if (serviceResults == null) {
			messages.append("Erroneously retrieved null for CFSecServiceService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + serviceResults.size() + " entities from CFSec.Service\n");
		}

		List<?> serviceTypeResults = cFSecServiceTypeService.findAll();
		if (serviceTypeResults == null) {
			messages.append("Erroneously retrieved null for CFSecServiceTypeService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + serviceTypeResults.size() + " entities from CFSec.ServiceType\n");
		}

		List<?> sysClusterResults = cFSecSysClusterService.findAll();
		if (sysClusterResults == null) {
			messages.append("Erroneously retrieved null for CFSecSysClusterService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + sysClusterResults.size() + " entities from CFSec.SysCluster\n");
		}

		List<?> tenantResults = cFSecTenantService.findAll();
		if (tenantResults == null) {
			messages.append("Erroneously retrieved null for CFSecTenantService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + tenantResults.size() + " entities from CFSec.Tenant\n");
		}

		List<?> tSecGroupResults = cFSecTSecGroupService.findAll();
		if (tSecGroupResults == null) {
			messages.append("Erroneously retrieved null for CFSecTSecGroupService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + tSecGroupResults.size() + " entities from CFSec.TSecGroup\n");
		}

		List<?> tSecGrpIncResults = cFSecTSecGrpIncService.findAll();
		if (tSecGrpIncResults == null) {
			messages.append("Erroneously retrieved null for CFSecTSecGrpIncService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + tSecGrpIncResults.size() + " entities from CFSec.TSecGrpInc\n");
		}

		List<?> tSecGrpMembResults = cFSecTSecGrpMembService.findAll();
		if (tSecGrpMembResults == null) {
			messages.append("Erroneously retrieved null for CFSecTSecGrpMembService.findAll()\n");
		}
		else {
			messages.append("Retrieved " + tSecGrpMembResults.size() + " entities from CFSec.TSecGrpMemb\n");
		}

		messages.append("CFSec tests complete\n");
		return( messages.toString() );
	}
}
