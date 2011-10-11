// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.mdm.repository.core.hash.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.talend.mdm.commmon.util.hash.IHashValueCalculator;
import org.talend.mdm.repository.core.hash.routingrule.EObjRoutingRuleHVCalculator;
import org.talend.mdm.repository.core.hash.routingrule.WSRoutingRuleHVCalculator;
import org.talend.mdm.repository.model.mdmserverobject.MdmserverobjectFactory;
import org.talend.mdm.repository.model.mdmserverobject.WSRoutingRuleE;
import org.talend.mdm.repository.model.mdmserverobject.WSRoutingRuleExpressionE;
import org.talend.mdm.repository.model.mdmserverobject.WSRoutingRuleOperatorE;

import com.amalto.workbench.webservices.WSRoutingRule;
import com.amalto.workbench.webservices.WSRoutingRuleExpression;
import com.amalto.workbench.webservices.WSRoutingRuleOperator;

/**
 * DOC hbhong class global comment. Detailled comment
 */
public class TestRoutingRule {

    private WSRoutingRule initWSObject() {
        WSRoutingRule rule = new WSRoutingRule();
        rule.setName("ruleNameA");
        rule.setConcept("concept");
        rule.setCondition("condition");
        rule.setDeactive(true);
        rule.setDescription("description");
        rule.setParameters("parameters");
        rule.setServiceJNDI("serviceJNDI");
        rule.setSynchronous(false);
        //
        WSRoutingRuleExpression e1 = new WSRoutingRuleExpression();
        e1.setName("expression1");
        e1.setValue("value1");
        e1.setXpath("xpath");
        //
        WSRoutingRuleOperator o1 = new WSRoutingRuleOperator();
        o1.setValue("opA");
        e1.setWsOperator(o1);
        //
        WSRoutingRuleExpression e2 = new WSRoutingRuleExpression();
        e2.setName("expression2");
        e2.setValue("value2");
        e2.setXpath("xpath2");
        //
        WSRoutingRuleOperator o2 = new WSRoutingRuleOperator();
        o2.setValue("opB");
        e2.setWsOperator(o2);
        WSRoutingRuleExpression[] ex = new WSRoutingRuleExpression[] { e1, e2 };
        rule.setWsRoutingRuleExpressions(ex);
        //

        return rule;
    }

    private WSRoutingRuleE initEObjRule() {
        WSRoutingRuleE rule = MdmserverobjectFactory.eINSTANCE.createWSRoutingRuleE();
        rule.setName("ruleNameA");
        rule.setConcept("concept");
        rule.setCondition("condition");
        rule.setDeactive(true);
        rule.setDescription("description");
        rule.setParameters("parameters");
        rule.setServiceJNDI("serviceJNDI");
        rule.setSynchronous(false);
        //
        WSRoutingRuleExpressionE e1 = MdmserverobjectFactory.eINSTANCE.createWSRoutingRuleExpressionE();
        e1.setName("expression1");
        e1.setValue("value1");
        e1.setXpath("xpath");
        //
        WSRoutingRuleOperatorE o1 = MdmserverobjectFactory.eINSTANCE.createWSRoutingRuleOperatorE();
        o1.setValue("opA");
        e1.setWsOperator(o1);
        //
        WSRoutingRuleExpressionE e2 = MdmserverobjectFactory.eINSTANCE.createWSRoutingRuleExpressionE();
        e2.setName("expression2");
        e2.setValue("value2");
        e2.setXpath("xpath2");
        //
        WSRoutingRuleOperatorE o2 = MdmserverobjectFactory.eINSTANCE.createWSRoutingRuleOperatorE();
        o2.setValue("opB");
        e2.setWsOperator(o2);

        rule.getWsRoutingRuleExpressions().add(e1);
        rule.getWsRoutingRuleExpressions().add(e2);
        return rule;
    }

    @Test
    public void testHash() {
        IHashValueCalculator wsCalculator = new WSRoutingRuleHVCalculator();
        IHashValueCalculator eobjCalculator = new EObjRoutingRuleHVCalculator();
        WSRoutingRule wsRule = initWSObject();
        WSRoutingRuleE eobjRule = initEObjRule();
        long wsHash = wsCalculator.calculateHash(wsRule);
        long eobjHash = eobjCalculator.calculateHash(eobjRule);
        assertEquals(wsHash, eobjHash);
    }

}
