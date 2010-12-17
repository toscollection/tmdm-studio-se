// ============================================================================
//
// Copyright (C) 2006-2010 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.mdm.studio.test.eventmanagement.process;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.talend.mdm.studio.test.eventmanagement.EventManagementTest;

/**
 * DOC rhou class global comment. Detailled comment
 */
public class ProcessParentOperationTest extends EventManagementTest {

    private static SWTBotTreeItem processParentNode;

    private String selEle = "Reporting";

    @Before
    public void runBeforeEveryTest() {

    }

    @After
    public void runAfterEveryTest() {

    }

    @BeforeClass
    public static void runBeforeClass() {
        // run for one time before all test cases
        processParentNode = eventManagementItem.getNode("Process [HEAD]");
    }

    @AfterClass
    public static void runAfterClass() {
        // run for one time after all test cases
    }

    @Test
    public void newTest() {
        // for normal process
        processParentNode.contextMenu("New").click();
        bot.text().setText("Normal_Process");
        bot.radio("Create a Normal Process").click();
        bot.button("OK").click();
        sleep();
        Assert.assertNotNull(processParentNode.expand().getNode("Normal_Process"));

        // for smartview process
        processParentNode.contextMenu("New").click();
        bot.radio("Create a Smartview Process").click();
        selecteXpath(selEle);
        bot.button("OK").click();
        sleep();
        Assert.assertNotNull(processParentNode.expand().getNode("Smart_view_" + selEle));

        // for normal process
        processParentNode.contextMenu("New").click();
        bot.radio("Create a Before-Saving Process").click();
        selecteXpath(selEle);
        bot.button("OK").click();
        sleep();
        Assert.assertNotNull(processParentNode.expand().getNode("beforeSaving_" + selEle));

        // for normal process
        processParentNode.contextMenu("New").click();
        bot.radio("Create a Before-Deleting Process").click();
        selecteXpath(selEle);
        bot.button("OK").click();
        sleep();
        Assert.assertNotNull(processParentNode.expand().getNode("beforeDeleting_" + selEle));

        // for normal process
        processParentNode.contextMenu("New").click();
        bot.radio("Create a Runable Process").click();
        selecteXpath(selEle);
        bot.button("OK").click();
        sleep();
        Assert.assertNotNull(processParentNode.expand().getNode("Runnable_" + selEle));

        // for normal process
        processParentNode.contextMenu("New").click();
        bot.radio("Create a Standalone Process").click();
        selecteXpath(selEle);
        bot.button("OK").click();
        sleep();
        Assert.assertNotNull(processParentNode.expand().getNode("Runnable#" + selEle));
    }

    @Test
    public void browseRevisionTest() {
        processParentNode.contextMenu("Browse Revision").click();
    }

    @Test
    public void newCategoryTest() {
        processParentNode.contextMenu("New Category").click();
        // bot.sleep(1000);
        SWTBotShell newCategoryShell = bot.shell("New Category");
        newCategoryShell.activate();
        SWTBotText text = bot.textWithLabel("Enter a name for the New Category");
        text.setText("TestProcessCategory");
        bot.button("OK").click();
        Assert.assertNotNull(processParentNode.expand().getNode("TestProcessCategory"));
        sleep(2);
        processParentNode.getNode("TestProcessCategory").contextMenu("Delete").click();
        sleep();
        bot.button("OK").click();
        sleep();
        Assert.assertNull(processParentNode.expand().getNode("TestProcessCategory"));
    }

    private void selecteXpath(String selEle) {
        bot.buttonWithTooltip("Select one Entity").click();
        bot.comboBox().setSelection(selEle);
        sleep();
        bot.tree().select(selEle);
        sleep();
        bot.button("Add").click();
        sleep();
    }
}
