// ============================================================================
//
// Copyright (C) 2006-2021 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package com.amalto.workbench.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.impl.XSDSchemaImpl;
import org.eclipse.xsd.util.XSDConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.service.IMDMWebServiceHook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.amalto.workbench.service.MissingJarService;
import com.amalto.workbench.webservices.TMDMService;
import com.amalto.workbench.webservices.TMDMService_Service;

/**
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Util.class, MissingJarService.class, GlobalServiceRegister.class })
@PowerMockIgnore({
        "com.sun.org.apache.xerces.*", "javax.xml.catalog.*", "javax.xml.parsers.*", "org.w3c.dom.*", "org.xml.sax.*" })
public class UtilMockTest {

    private Logger log = Logger.getLogger(UtilMockTest.class);

    @Test
    public void testCheckAndAddSuffix() {
        String suffix = "?wsdl"; //$NON-NLS-1$
        String method = "checkAndAddSuffix"; //$NON-NLS-1$
        try {
            Method testMethod = Util.class.getDeclaredMethod(method, URL.class);
            testMethod.setAccessible(true);

            // http
            String url_str = "http://localhost:8180/talendmdm/services/soap"; //$NON-NLS-1$
            URL url_in = new URL(url_str);
            URL url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str + suffix, url_result.toString());

            url_str = "http://localhost:8180/talendmdm/services/soap?wsdl"; //$NON-NLS-1$
            url_in = new URL(url_str);
            url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str, url_result.toString());

            // https
            url_str = "https://localhost:8543/talendmdm/services/soap"; //$NON-NLS-1$
            url_in = new URL(url_str);
            url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str + suffix, url_result.toString());

            url_str = "https://localhost:8543/talendmdm/services/soap?wsdl"; //$NON-NLS-1$
            url_in = new URL(url_str);
            url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str, url_result.toString());

            // ftp
            url_str = "ftp://localhost:8543/talendmdm/services/soap"; //$NON-NLS-1$
            url_in = new URL(url_str);
            url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str, url_result.toString());

            url_str = "ftp://localhost:8543/talendmdm/services/soap?wsdl"; //$NON-NLS-1$
            url_in = new URL(url_str);
            url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str, url_result.toString());

            // file
            url_str = "file://d:/dir/Product.proc"; //$NON-NLS-1$
            url_in = new URL(url_str);
            url_result = (URL) testMethod.invoke(null, url_in);
            assertEquals(url_str, url_result.toString());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testIsReferrenced() {
        try {
            String method = "isReferrenced"; //$NON-NLS-1$
            Method testMethod = Util.class.getDeclaredMethod(method, XSDElementDeclaration.class, XSDElementDeclaration.class);
            testMethod.setAccessible(true);

            XSDElementDeclaration xsdElementDeclaration1 = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            XSDElementDeclaration xsdElementDeclaration2 = XSDFactory.eINSTANCE.createXSDElementDeclaration();

            //
            boolean isReferenced = (boolean) testMethod.invoke(null, xsdElementDeclaration1, xsdElementDeclaration1);
            assertTrue(isReferenced);

            //
            isReferenced = (boolean) testMethod.invoke(null, xsdElementDeclaration1, xsdElementDeclaration2);
            assertFalse(isReferenced);

            //
            XSDParticle xsdParticle = buildXSDParticleWithChildren(3, new String[] { "a", "b", "c" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
            xsdComplexTypeDefinition.setContent(xsdParticle);
            xsdElementDeclaration2.setTypeDefinition(xsdComplexTypeDefinition);
            isReferenced = (boolean) testMethod.invoke(null, xsdElementDeclaration1, xsdElementDeclaration2);
            assertFalse(isReferenced);

            //
            XSDParticle xsdParticle1 = XSDFactory.eINSTANCE.createXSDParticle();
            xsdParticle1.setTerm(xsdElementDeclaration1);
            XSDModelGroup modelGroup = ((XSDModelGroup) xsdParticle.getTerm());
            modelGroup.getContents().add(xsdParticle1);
            isReferenced = (boolean) testMethod.invoke(null, xsdElementDeclaration1, xsdElementDeclaration2);
            assertTrue(isReferenced);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private XSDParticle buildXSDParticleWithChildren(int childrenNumb, String[] childrenNames) {
        XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();

        XSDModelGroup xsdModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
        xsdParticle.setTerm(xsdModelGroup);

        for (int i = 0; i < childrenNumb && i < childrenNames.length; i++) {
            XSDParticle xsdParticle_child = XSDFactory.eINSTANCE.createXSDParticle();
            XSDElementDeclaration xsdElementDeclaration_child = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            xsdElementDeclaration_child.setName(childrenNames[i]);
            xsdParticle_child.setTerm(xsdElementDeclaration_child);
            xsdModelGroup.getContents().add(xsdParticle_child);
        }

        return xsdParticle;
    }

    @Test
    public void testGetParent() {
        Object parent = Util.getParent(null);
        assertNull(parent);

        XSDElementDeclaration concept = XSDFactory.eINSTANCE.createXSDElementDeclaration();
        XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
        parent = Util.getParent(concept);
        assertNull(parent);
        parent = Util.getParent(xsdParticle);
        assertNull(parent);

        XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();
        xsdSchema.getContents().add(concept);
        parent = Util.getParent(concept);
        assertSame(concept, parent);// concept's parent is itself

        //
        try {
            XSDComplexTypeDefinition xsdComplexTypeDef = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
            xsdComplexTypeDef.setBaseTypeDefinition(
                    xsdSchema.resolveComplexTypeDefinition(xsdSchema.getSchemaForSchemaNamespace(), "anyType")); //$NON-NLS-1$
            XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
            xsdComplexTypeDef.setContent(particle);
            concept.setAnonymousTypeDefinition(xsdComplexTypeDef);

            XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
            particle.setContent(modelGroup);

            XSDParticle particle1 = XSDFactory.eINSTANCE.createXSDParticle();
            XSDElementDeclaration elementDeclaration1 = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            particle1.setContent(elementDeclaration1);

            XSDParticle particle2 = XSDFactory.eINSTANCE.createXSDParticle();
            XSDElementDeclaration elementDeclaration2 = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            particle2.setContent(elementDeclaration2);
            // add children to elementDeclaration2
            XSDComplexTypeDefinition complexType2 = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
            complexType2.setBaseTypeDefinition(
                    xsdSchema.resolveComplexTypeDefinition(xsdSchema.getSchemaForSchemaNamespace(), "anyType")); //$NON-NLS-1$
            XSDModelGroup modelGroup_type = XSDFactory.eINSTANCE.createXSDModelGroup();
            XSDParticle particle_child = XSDFactory.eINSTANCE.createXSDParticle();
            XSDElementDeclaration elementDeclaration_child = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            particle_child.setContent(elementDeclaration_child);
            modelGroup_type.getContents().add(particle_child);
            XSDParticle particle_type = XSDFactory.eINSTANCE.createXSDParticle();
            particle_type.setContent(modelGroup_type);
            complexType2.setContent(particle_type);
            elementDeclaration2.setAnonymousTypeDefinition(complexType2);

            modelGroup.getContents().add(particle1);
            modelGroup.getContents().add(particle2);

            parent = Util.getParent(elementDeclaration1);
            assertSame(concept, parent);
            parent = Util.getParent(elementDeclaration2);
            assertSame(concept, parent);

            XSDElementDeclaration son = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            parent = Util.getParent(son);
            assertNull(parent);

            //
            xsdSchema.getContents().clear();
            xsdSchema.getContents().add(xsdComplexTypeDef);
            parent = Util.getParent(elementDeclaration1);
            assertSame(xsdComplexTypeDef, parent);
            parent = Util.getParent(elementDeclaration2);
            assertSame(xsdComplexTypeDef, parent);

            //
            parent = Util.getParent(elementDeclaration_child);
            assertSame(elementDeclaration2, parent);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindOutSpecialSonElement() {
        String method_findspecial = "findOutSpecialSonElement"; //$NON-NLS-1$

        Set<XSDConcreteComponent> complexTypes = new HashSet<XSDConcreteComponent>();
        String[] names = { "ele1", "ele2", "ele3", "ele4" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

        try {
            XSDFactory factory = XSDFactory.eINSTANCE;
            XSDElementDeclaration parent = factory.createXSDElementDeclaration();
            XSDComplexTypeDefinition parentCType = factory.createXSDComplexTypeDefinition();
            parent.setTypeDefinition(parentCType);
            XSDParticle particle = factory.createXSDParticle();
            parentCType.setContent(particle);
            XSDModelGroup modelGroup = factory.createXSDModelGroup();
            particle.setTerm(modelGroup);

            XSDParticle particle1 = factory.createXSDParticle();
            XSDElementDeclaration xsdElementDeclaration1 = factory.createXSDElementDeclaration();
            xsdElementDeclaration1.setName(names[0]);
            particle1.setContent(xsdElementDeclaration1);

            XSDParticle particle2 = factory.createXSDParticle();
            XSDElementDeclaration xsdElementDeclaration2 = factory.createXSDElementDeclaration();
            xsdElementDeclaration2.setName(names[1]);
            particle2.setContent(xsdElementDeclaration2);

            List<XSDParticle> particles = new ArrayList<XSDParticle>();
            particles.add(particle1);
            particles.add(particle2);
            modelGroup.getContents().addAll(particles);
            //

            Method findSpecialSonElementMethod = Util.class.getDeclaredMethod(method_findspecial, XSDElementDeclaration.class,
                    XSDElementDeclaration.class, Set.class);
            findSpecialSonElementMethod.setAccessible(true);
            Object result = findSpecialSonElementMethod.invoke(null, parent, xsdElementDeclaration1, complexTypes);
            assertSame(parent, result);

            //
            XSDComplexTypeDefinition cType_xsdElementDeclaration2 = factory.createXSDComplexTypeDefinition();
            xsdElementDeclaration2.setTypeDefinition(cType_xsdElementDeclaration2);
            XSDParticle particle_cType_xsdElementDeclaration2 = factory.createXSDParticle();
            cType_xsdElementDeclaration2.setContent(particle_cType_xsdElementDeclaration2);
            XSDModelGroup modelGroup_cType_xsdElementDeclaration2 = factory.createXSDModelGroup();
            particle_cType_xsdElementDeclaration2.setTerm(modelGroup_cType_xsdElementDeclaration2);
            XSDParticle childParticle = factory.createXSDParticle();
            modelGroup_cType_xsdElementDeclaration2.getContents().add(childParticle);
            XSDElementDeclaration childElementDeclaration = factory.createXSDElementDeclaration();
            childParticle.setContent(childElementDeclaration);

            complexTypes.clear();
            result = findSpecialSonElementMethod.invoke(null, parent, childElementDeclaration, complexTypes);
            assertSame(xsdElementDeclaration2, result);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindOutAllSonElements() {
        XSDFactory factory = XSDFactory.eINSTANCE;
        Set<XSDConcreteComponent> complexTypes = new HashSet<XSDConcreteComponent>();

        String[] targetNameSpaces = { "http://www.w3.org/2001/XMLSchema", "", "" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String[] names = { "p1", "p2", "p3" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

        XSDTypeDefinition[] types = { factory.createXSDSimpleTypeDefinition(), factory.createXSDSimpleTypeDefinition(),
                factory.createXSDSimpleTypeDefinition() };

        XSDElementDeclaration xsdElementDeclaration = factory.createXSDElementDeclaration();
        xsdElementDeclaration.setName(names[0]);
        xsdElementDeclaration.setTargetNamespace(targetNameSpaces[0]);
        XSDComplexTypeDefinition xsdComplexTypeDef = factory.createXSDComplexTypeDefinition();
        xsdElementDeclaration.setAnonymousTypeDefinition(xsdComplexTypeDef);
        XSDParticle typeParticle = factory.createXSDParticle();
        xsdComplexTypeDef.setContent(typeParticle);

        XSDModelGroup xsdModelGroup = factory.createXSDModelGroup();
        for (int i = 0; i < names.length; i++) {
            XSDElementDeclaration xsdEleDec = factory.createXSDElementDeclaration();
            xsdEleDec.setName(names[i]);
            xsdEleDec.setTargetNamespace(targetNameSpaces[i]);
            if (i == 0) {
                xsdEleDec.setTypeDefinition(xsdComplexTypeDef);
            } else {
                xsdEleDec.setTypeDefinition(types[i]);
            }
            XSDParticle xsdParticle = factory.createXSDParticle();
            xsdParticle.setContent(xsdEleDec);
            xsdModelGroup.getContents().add(xsdParticle);
        }
        typeParticle.setContent(xsdModelGroup);

        try {
            String methodToExecute = "findOutAllSonElements"; //$NON-NLS-1$
            Method testMethod = Util.class.getDeclaredMethod(methodToExecute, XSDElementDeclaration.class, Set.class);
            testMethod.setAccessible(true);
            List<XSDElementDeclaration> allson = (List<XSDElementDeclaration>) testMethod.invoke(null, xsdElementDeclaration,
                    complexTypes);
            assertNotNull(allson);
            assertTrue(allson.size() == 2);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    @Test
    public void testUpdateReferenceToXSDTypeDefinition() {
        try {
            XSDFactory factory = XSDFactory.eINSTANCE;
            XSDSchema schema = factory.createXSDSchema();

            XSDComplexTypeDefinition ctype1 = Mockito.spy(factory.createXSDComplexTypeDefinition());
            ctype1.setName("ctype1");
            when(ctype1.getSchema()).thenReturn(schema);
            XSDComplexTypeDefinition ctype2 = Mockito.spy(factory.createXSDComplexTypeDefinition());
            ctype2.setName("ctype2");
            when(ctype2.getSchema()).thenReturn(schema);
            XSDSimpleTypeDefinition stype = Mockito.spy(factory.createXSDSimpleTypeDefinition());
            stype.setName("stype");
            when(stype.getSchema()).thenReturn(schema);

            XSDTypeDefinition[] newTypes = { ctype1, stype };
            schema.getTypeDefinitions().addAll(Arrays.asList(ctype1, ctype2, stype));

            for (XSDTypeDefinition newType : newTypes) {
                //
                XSDElementDeclaration mockElementDecl = mock(XSDElementDeclaration.class);//
                when(mockElementDecl.getTypeDefinition()).thenReturn(newType);

                //
                XSDParticle xsdParticle1 = mock(XSDParticle.class);//
                XSDElementDeclaration mockElementDec2 = mock(XSDElementDeclaration.class);
                when(mockElementDec2.getTypeDefinition()).thenReturn(newType);
                when(xsdParticle1.getTerm()).thenReturn(mockElementDec2);

                //
                XSDParticle xsdParticle2 = mock(XSDParticle.class);//

                XSDParticle particle_c1 = mock(XSDParticle.class);
                XSDElementDeclaration mockElementDec_c1 = mock(XSDElementDeclaration.class);
                when(mockElementDec_c1.getTypeDefinition()).thenReturn(newType);
                when(particle_c1.getContent()).thenReturn(mockElementDec_c1);

                XSDParticle particle_c2 = mock(XSDParticle.class);
                XSDElementDeclaration mockElementDec_c2 = mock(XSDElementDeclaration.class);
                when(mockElementDec_c2.getTypeDefinition()).thenReturn(newType);
                when(particle_c2.getContent()).thenReturn(mockElementDec_c2);

                XSDParticle particle_c3 = mock(XSDParticle.class);
                XSDElementDeclaration mockElementDec_c3 = mock(XSDElementDeclaration.class);
                when(mockElementDec_c3.getTypeDefinition()).thenReturn(ctype1);
                when(particle_c3.getContent()).thenReturn(mockElementDec_c3);

                XSDModelGroup xsdModelGroup = mock(XSDModelGroup.class);
                EList<XSDParticle> elist = new BasicEList<XSDParticle>();
                elist.add(particle_c1);
                elist.add(particle_c2);
                elist.add(particle_c3);
                when(xsdModelGroup.getContents()).thenReturn(elist);
                when(xsdParticle2.getTerm()).thenReturn(xsdModelGroup);

                Object[] allNodes = new Object[] { mockElementDecl, xsdParticle1, xsdParticle2 };

                //
                IStructuredContentProvider mockContentProvider = mock(IStructuredContentProvider.class);
                when(mockContentProvider.getElements(Mockito.any())).thenReturn(allNodes);
                Util.updateReferenceToXSDTypeDefinition(new Object(), newType, mockContentProvider);

                Mockito.verify(mockElementDecl).setTypeDefinition(newType);
                Mockito.verify(mockElementDec2).setTypeDefinition(newType);
                Mockito.verify(mockElementDec_c1).setTypeDefinition(newType);
                Mockito.verify(mockElementDec_c2).setTypeDefinition(newType);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testUpdatePrimaryKeyInfo() {
        String method_private = "updatePrimaryKeyInfo"; //$NON-NLS-1$
        String app_primaryKey = "X_PrimaryKeyInfo"; //$NON-NLS-1$
        XSDFactory factory = XSDFactory.eINSTANCE;

        try {
            Method testMethod = Util.class.getDeclaredMethod(method_private, XSDElementDeclaration.class, String.class,
                    String.class);
            testMethod.setAccessible(true);

            XSDElementDeclaration concept = factory.createXSDElementDeclaration();
            XSDAnnotation conceptAnnotation = factory.createXSDAnnotation();
            concept.setAnnotation(conceptAnnotation);
            XSDSchema xsdSchema = factory.createXSDSchema();
            xsdSchema.getContents().add(concept);

            String pk1 = "Product/Id";//$NON-NLS-1$
            String pk2 = "Product/code";//$NON-NLS-1$
            String attr_key = "source";//$NON-NLS-1$
            String namespaceURI = "http://www.w3.org/XML/1998/namespace"; //$NON-NLS-1$

            Document doc = getEmptyDocument();
            xsdSchema.setDocument(doc);

            Element appinfoElement1 = doc.createElementNS(namespaceURI, "appinfo"); //$NON-NLS-1$
            appinfoElement1.setAttribute(attr_key, app_primaryKey);
            appinfoElement1.appendChild(doc.createTextNode(pk1));

            Element appinfoElement2 = doc.createElementNS(namespaceURI, "appinfosssss"); //$NON-NLS-1$
            appinfoElement2.setAttribute(attr_key, app_primaryKey);
            appinfoElement2.appendChild(doc.createTextNode(pk2));

            Element annoElement = doc.createElement("s"); //$NON-NLS-1$
            annoElement.appendChild(appinfoElement1);
            annoElement.appendChild(appinfoElement2);
            conceptAnnotation.setElement(annoElement);
            EList<Element> applicationInformations = conceptAnnotation.getApplicationInformation();
            applicationInformations.add(appinfoElement1);
            applicationInformations.add(appinfoElement2);

            String newValue = "Product_sufix/Id"; //$NON-NLS-1$
            testMethod.invoke(null, concept, pk1, newValue);
            assertEquals(newValue, annoElement.getChildNodes().item(1).getTextContent());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private Document getEmptyDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        return doc;
    }

    @Test
    public void testUpdateReference() {
        String oldValue = "oldValue", newValue = "newValue"; //$NON-NLS-1$ //$NON-NLS-2$

        XSDFactory factory = XSDFactory.eINSTANCE;
        try {
            Object[] objs = new Object[4];
            Object[] allForeignKeyAndInfos = new Object[0];

            Util.updateReference(new Object(), objs, allForeignKeyAndInfos, oldValue, newValue);

            //
            XSDModelGroup xsdModelGroup = factory.createXSDModelGroup();
            XSDElementDeclaration xsdEleDecl = mock(XSDElementDeclaration.class);

            XSDParticle particle1 = mock(XSDParticle.class);
            when(particle1.getTerm()).thenReturn(xsdEleDecl);
            XSDElementDeclaration mockDeclaration1 = mock(XSDElementDeclaration.class);
            when(mockDeclaration1.isElementDeclarationReference()).thenReturn(true);
            when(mockDeclaration1.getResolvedElementDeclaration()).thenReturn(xsdEleDecl);
            when(particle1.getContent()).thenReturn(mockDeclaration1);

            XSDParticle particle2 = mock(XSDParticle.class);
            when(particle2.getTerm()).thenReturn(xsdEleDecl);
            when(particle2.getContent()).thenReturn(xsdModelGroup);

            XSDParticle particle3 = mock(XSDParticle.class);
            when(particle3.getTerm()).thenReturn(xsdModelGroup);
            XSDElementDeclaration mockDeclaration3 = mock(XSDElementDeclaration.class);
            when(mockDeclaration3.isElementDeclarationReference()).thenReturn(true);
            when(mockDeclaration3.getResolvedElementDeclaration()).thenReturn(xsdEleDecl);
            when(particle3.getContent()).thenReturn(mockDeclaration3);

            XSDParticle particle4 = mock(XSDParticle.class);
            when(particle4.getTerm()).thenReturn(xsdModelGroup);
            when(particle4.getContent()).thenReturn(xsdModelGroup);

            objs[0] = particle1;
            objs[1] = particle2;
            objs[2] = particle3;
            objs[3] = particle4;

            Util.updateReference(xsdEleDecl, objs, allForeignKeyAndInfos, oldValue, newValue);
            verify(particle1).setTerm(xsdEleDecl);
            verify(particle1).updateElement();
            verify(mockDeclaration1).setResolvedElementDeclaration(xsdEleDecl);

            verify(particle2).setTerm(xsdEleDecl);
            verify(particle2).updateElement();

            verify(mockDeclaration3).setResolvedElementDeclaration(xsdEleDecl);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testGetAllForeignKeyRelatedInfos() {
        String localName = "appinfo"; //$NON-NLS-1$
        String attr_key = "source"; //$NON-NLS-1$
        String[] fkRelatedInfo = { "X_ForeignKey", "X_ForeignKeyInfo", "X_ForeignKey_Filter" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String namespaceURI = "http://www.w3.org/XML/1998/namespace"; //$NON-NLS-1$

        Object elem1 = XSDFactory.eINSTANCE.createXSDElementDeclaration();
        List<Object> objList = new ArrayList<Object>();
        Set<Object> visited = new HashSet<Object>();
        IStructuredContentProvider provider = mock(IStructuredContentProvider.class);

        Object[] allForeignKeyRelatedInfos = Util.getAllForeignKeyRelatedInfos(null, new ArrayList<Object>(), provider, visited);
        assertNull(allForeignKeyRelatedInfos);
        allForeignKeyRelatedInfos = Util.getAllForeignKeyRelatedInfos(new Object(), null, provider, visited);
        assertNull(allForeignKeyRelatedInfos);
        allForeignKeyRelatedInfos = Util.getAllForeignKeyRelatedInfos(new Object(), new ArrayList<Object>(), null, visited);
        assertNull(allForeignKeyRelatedInfos);
        allForeignKeyRelatedInfos = Util.getAllForeignKeyRelatedInfos(new Object(), new ArrayList<Object>(), provider, null);
        assertNull(allForeignKeyRelatedInfos);
        allForeignKeyRelatedInfos = Util.getAllForeignKeyRelatedInfos(new Object(), new ArrayList<Object>(), provider, visited);
        assertNotNull(allForeignKeyRelatedInfos);
        assertTrue(allForeignKeyRelatedInfos.length == 0);

        try {
            Document doc = getEmptyDocument();

            Object[] elems1 = new Object[4];
            for (int i = 0; i < elems1.length - 1; i++) {
                Element fkRelatedElement = doc.createElementNS(namespaceURI, localName);
                fkRelatedElement.setAttribute(attr_key, fkRelatedInfo[i]);
                elems1[i] = fkRelatedElement;
            }
            Object elem2 = XSDFactory.eINSTANCE.createXSDElementDeclaration();
            elems1[3] = elem2;

            Object[] elems2 = new Object[3];
            for (int i = 0; i < elems2.length; i++) {
                Element fkRelatedElement = doc.createElementNS(namespaceURI, localName);
                fkRelatedElement.setAttribute(attr_key, fkRelatedInfo[i]);
                elems2[i] = fkRelatedElement;
            }

            when(provider.getElements(eq(elem1))).thenReturn(elems1);
            when(provider.getElements(eq(elem2))).thenReturn(elems2);

            allForeignKeyRelatedInfos = Util.getAllForeignKeyRelatedInfos(elem1, objList, provider, visited);
            assertNotNull(allForeignKeyRelatedInfos);
            assertEquals(6, allForeignKeyRelatedInfos.length);
            assertArrayEquals(objList.toArray(), allForeignKeyRelatedInfos);

            for (int j = 0; j < elems1.length - 1; j++) {
                assertTrue(objList.contains(elems1[j]));
            }
            for (int j = 0; j < elems2.length; j++) {
                assertTrue(objList.contains(elems1[j]));
            }
        } catch (ParserConfigurationException e) {
            log.error(e.getMessage(), e);
        }

    }

    @Test
    public void testGetComplexChilds() {
        String methodToExecute = "getComplexChilds"; //$NON-NLS-1$
        String parentxpath = ""; //$NON-NLS-1$
        String[] names = { "simpleA", "complexB", "simpleC", "complexD" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        String[] childpath = { "complexB//aa", "complexD//bb" }; //$NON-NLS-1$ //$NON-NLS-2$
        String[] childrenNames = { "", "aa", "", "bb" }; //$NON-NLS-1$ //$NON-NLS-2$
        boolean onlyTopLevel = false;
        Set<Object> visited = new HashSet<Object>();

        XSDFactory factory = XSDFactory.eINSTANCE;
        XSDComplexTypeDefinition complexTypeDef = factory.createXSDComplexTypeDefinition();

        try {
            Method getComplexChildrenMethod = Util.class.getDeclaredMethod(methodToExecute, String.class,
                    XSDComplexTypeDefinition.class, boolean.class, Set.class);
            getComplexChildrenMethod.setAccessible(true);
            Map<String, XSDParticle> complexChilds = (Map<String, XSDParticle>) getComplexChildrenMethod.invoke(null, parentxpath,
                    complexTypeDef, true, null);
            assertNotNull(complexChilds);
            assertTrue(complexChilds.isEmpty());

            //
            XSDModelGroup group = factory.createXSDModelGroup();
            for (int i = 0; i < names.length; i++) {
                XSDParticle particle = factory.createXSDParticle();
                XSDElementDeclaration elementDecl = factory.createXSDElementDeclaration();
                XSDTypeDefinition xsdType = factory.createXSDSimpleTypeDefinition();
                if (i % 2 != 0) {
                    xsdType = factory.createXSDComplexTypeDefinition();
                    XSDParticle xsdTypeParticle = factory.createXSDParticle();
                    ((XSDComplexTypeDefinition) xsdType).setContent(xsdTypeParticle);

                    XSDModelGroup xsdModelGroup = factory.createXSDModelGroup();
                    xsdTypeParticle.setTerm(xsdModelGroup);

                    XSDParticle childParticle = factory.createXSDParticle();
                    XSDElementDeclaration childElement = factory.createXSDElementDeclaration();
                    childElement.setName(childrenNames[i]);
                    childParticle.setTerm(childElement);
                    xsdModelGroup.getParticles().add(childParticle);
                }
                elementDecl.setTypeDefinition(xsdType);
                elementDecl.setName(names[i]);
                particle.setTerm(elementDecl);
                group.getParticles().add(particle);
            }
            XSDParticle typeParticle = factory.createXSDParticle();
            typeParticle.setTerm(group);
            complexTypeDef.setContent(typeParticle);

            complexChilds = (Map<String, XSDParticle>) getComplexChildrenMethod.invoke(null, parentxpath, complexTypeDef,
                    onlyTopLevel, visited);
            assertTrue(complexChilds.keySet().contains("simpleA")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("simpleC")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("//complexB")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("//complexD")); //$NON-NLS-1$
            if (complexChilds.size() == 6) {
                assertTrue(complexChilds.keySet().contains(childpath[0]));
                assertTrue(complexChilds.keySet().contains(childpath[1]));
            }

            onlyTopLevel = true;
            complexChilds = (Map<String, XSDParticle>) getComplexChildrenMethod.invoke(null, parentxpath, complexTypeDef,
                    onlyTopLevel, visited);
            assertTrue(complexChilds.keySet().contains("simpleA")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("simpleC")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("//complexB")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("//complexD")); //$NON-NLS-1$

            //
            parentxpath = "parentXPath"; //$NON-NLS-1$
            onlyTopLevel = false;
            visited.clear();
            complexChilds = (Map<String, XSDParticle>) getComplexChildrenMethod.invoke(null, parentxpath, complexTypeDef,
                    onlyTopLevel, visited);
            assertTrue(complexChilds.keySet().contains("parentXPath/simpleA")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("parentXPath/simpleC")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("parentXPath//complexB")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("parentXPath//complexD")); //$NON-NLS-1$
            if (complexChilds.size() == 6) {
                assertTrue(complexChilds.keySet().contains(parentxpath + "/" + childpath[0]));
                assertTrue(complexChilds.keySet().contains(parentxpath + "/" + childpath[1]));
            }

            //
            onlyTopLevel = true;
            visited.clear();
            complexChilds = (Map<String, XSDParticle>) getComplexChildrenMethod.invoke(null, parentxpath, complexTypeDef,
                    onlyTopLevel, visited);
            assertTrue(complexChilds.keySet().contains("parentXPath/simpleA")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("parentXPath/simpleC")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("parentXPath//complexB")); //$NON-NLS-1$
            assertTrue(complexChilds.keySet().contains("parentXPath//complexD")); //$NON-NLS-1$
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testIsAImporedElement() {
        XSDFactory factory = XSDFactory.eINSTANCE;

        XSDSchema xsdSchema = factory.createXSDSchema();
        XSDElementDeclaration elementDeclaration = factory.createXSDElementDeclaration();
        XSDComplexTypeDefinition complexTypeDefinition = factory.createXSDComplexTypeDefinition();
        complexTypeDefinition.setBaseTypeDefinition(
                xsdSchema.resolveComplexTypeDefinition(xsdSchema.getSchemaForSchemaNamespace(), "anyType")); //$NON-NLS-1$
        XSDParticle typeParticle = factory.createXSDParticle();
        XSDModelGroup modelGroup = factory.createXSDModelGroup();
        XSDParticle particle = factory.createXSDParticle();
        XSDElementDeclaration pelement = factory.createXSDElementDeclaration();

        elementDeclaration.setAnonymousTypeDefinition(complexTypeDefinition);
        complexTypeDefinition.setContent(typeParticle);
        typeParticle.setContent(modelGroup);
        modelGroup.getContents().add(particle);
        particle.setContent(pelement);

        boolean isAImporedElement = Util.IsAImporedElement(null, xsdSchema);
        assertFalse(isAImporedElement);
        isAImporedElement = Util.IsAImporedElement(pelement, xsdSchema);
        assertFalse(isAImporedElement);

        xsdSchema.getContents().add(elementDeclaration);
        isAImporedElement = Util.IsAImporedElement(pelement, xsdSchema);
        assertFalse(isAImporedElement);

        XSDSchema anotherSchema = factory.createXSDSchema();
        isAImporedElement = Util.IsAImporedElement(pelement, anotherSchema);
        assertFalse(isAImporedElement);

        xsdSchema.getReferencingDirectives().add(factory.createXSDImport());
        xsdSchema.getReferencingDirectives().add(factory.createXSDInclude());
        isAImporedElement = Util.IsAImporedElement(pelement, anotherSchema);
        assertTrue(isAImporedElement);

    }

    @Test
    public void testAddImport() {// 2184
        String namespace1 = "http://www.w3.org/XML/1998/namespace1"; //$NON-NLS-1$
        String namespace2 = "http://www.w3.org/XML/1998/namespace2"; //$NON-NLS-1$
        String namespace3 = "http://www.w3.org/XML/1998/namespace3"; //$NON-NLS-1$
        String[] namespaces = { namespace1, namespace2, namespace3 };

        String methodToExecute = "addImport"; //$NON-NLS-1$
        XSDFactory factory = XSDFactory.eINSTANCE;
        XSDSchema xsdschema = factory.createXSDSchema();

        List<XSDImport> imports = new ArrayList<XSDImport>();
        for (int i = 0; i < 3; i++) {
            XSDImport importt = factory.createXSDImport();
            importt.setNamespace(namespaces[i]);
            imports.add(importt);
        }

        XSDImport importt = factory.createXSDImport();
        importt.setNamespace(""); //$NON-NLS-1$
        imports.add(importt);

        xsdschema.getContents().add(imports.get(0));

        try {
            Method testMethod = Util.class.getDeclaredMethod(methodToExecute, XSDSchema.class, List.class);
            testMethod.setAccessible(true);
            testMethod.invoke(null, xsdschema, imports);
            EList<XSDSchemaContent> contents = xsdschema.getContents();
            assertTrue(contents.size() == 3);
            assertTrue(contents.contains(imports.get(0)));
            assertTrue(contents.contains(imports.get(1)));
            assertTrue(contents.contains(imports.get(2)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testGetComplexTypes() {
        XSDFactory factory = XSDFactory.eINSTANCE;
        XSDSchema xSchema = factory.createXSDSchema();
        List<XSDComplexTypeDefinition> complexTypes = Util.getComplexTypes(xSchema);
        assertNotNull(complexTypes);
        assertTrue(complexTypes.isEmpty());

        XSDSimpleTypeDefinition simpleTypeDefinition = factory.createXSDSimpleTypeDefinition();
        simpleTypeDefinition
        .setBaseTypeDefinition(xSchema.resolveSimpleTypeDefinition(xSchema.getSchemaForSchemaNamespace(), "string")); //$NON-NLS-1$ );
        xSchema.getContents().add(simpleTypeDefinition);
        complexTypes = Util.getComplexTypes(xSchema);
        assertNotNull(complexTypes);
        assertTrue(complexTypes.isEmpty());

        XSDComplexTypeDefinition baseComplexTypeDefinition = xSchema
                .resolveComplexTypeDefinition(xSchema.getSchemaForSchemaNamespace(), "anyType"); //$NON-NLS-1$

        XSDComplexTypeDefinition complexType1 = factory.createXSDComplexTypeDefinition();
        complexType1.setBaseTypeDefinition(baseComplexTypeDefinition);
        complexType1.setTargetNamespace(null);
        complexType1.setName("ctype1"); //$NON-NLS-1$
        XSDComplexTypeDefinition complexType2 = factory.createXSDComplexTypeDefinition();
        complexType2.setTargetNamespace("targetNameSpace"); //$NON-NLS-1$
        complexType2.setName("ctype2"); //$NON-NLS-1$
        complexType2.setBaseTypeDefinition(baseComplexTypeDefinition);
        XSDComplexTypeDefinition complexType3 = factory.createXSDComplexTypeDefinition();
        complexType3.setTargetNamespace(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
        complexType3.setName("topLevelComplexType"); //$NON-NLS-1$
        complexType3.setBaseTypeDefinition(baseComplexTypeDefinition);

        EList<XSDTypeDefinition> contents = new BasicEList<XSDTypeDefinition>();
        contents.add(complexType1);
        contents.add(complexType2);
        contents.add(complexType3);

        XSDSchema spySchema = spy(xSchema);
        when(spySchema.getTypeDefinitions()).thenReturn(contents);

        complexTypes = Util.getComplexTypes(spySchema);
        assertNotNull(complexTypes);
        assertTrue(complexTypes.size() == 2);
        assertTrue(complexTypes.contains(complexType1));
        assertTrue(complexTypes.contains(complexType2));
        assertFalse(complexTypes.contains(complexType3));

    }

    @Test
    public void testGetSimpleTypeDefinitionChildren() {
        XSDFactory factory = XSDFactory.eINSTANCE;
        XSDSimpleTypeDefinition simpleTypeDefinition = factory.createXSDSimpleTypeDefinition();

        try {
            //
            List<Object> children = Util.getSimpleTypeDefinitionChildren(simpleTypeDefinition);
            assertNotNull(children);
            assertTrue(children.size() == 1);
            assertTrue(children.contains(simpleTypeDefinition));

            //
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setNamespaceAware(true);
            documentBuilderFactory.setValidating(false);
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(getClass().getResourceAsStream("Product_0.1.xsd"));
            XSDSchema schema = XSDSchemaImpl.createSchema(document.getDocumentElement());
            XSDSimpleTypeDefinition stringTypeDefinition = schema
                    .resolveSimpleTypeDefinition(schema.getSchemaForSchemaNamespace(), "string");
            children = Util.getSimpleTypeDefinitionChildren(stringTypeDefinition);
            assertNotNull(children);
            assertTrue(children.size() == 1);
            assertTrue(children.contains(stringTypeDefinition));

            //
            XSDAnnotation annotation1 = factory.createXSDAnnotation();
            XSDAnnotation annotation2 = factory.createXSDAnnotation();
            simpleTypeDefinition.getAnnotations().add(annotation1);
            simpleTypeDefinition.getAnnotations().add(annotation2);
            children = Util.getSimpleTypeDefinitionChildren(simpleTypeDefinition);
            assertNotNull(children);
            assertTrue(children.size() == 3);
            assertTrue(children.contains(simpleTypeDefinition));
            assertTrue(children.contains(annotation1));
            assertTrue(children.contains(annotation2));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testGetMDMService4Params() throws Exception {
        String username = "username";
        String password = "password";
        String _url = "http://localhost:8180/talendmdm/services/soap?wsdl";
        URL url = new URL(_url);

        // set test method to be real call method
        PowerMockito.mockStatic(Util.class);
        PowerMockito.doCallRealMethod().when(Util.class, "getMDMService", any(URL.class), anyString(), anyString(), eq(false));
        
        // handle Missing jar service
        PowerMockito.mockStatic(MissingJarService.class);
        MissingJarService mockMissingJarService = PowerMockito.mock(MissingJarService.class);
        PowerMockito.when(mockMissingJarService.checkMissingJar(anyBoolean())).thenReturn(true);
        PowerMockito.when(MissingJarService.getInstance()).thenReturn(mockMissingJarService);

        // mock IMDMWebServiceHook
        IMDMWebServiceHook webServiceHookStub = Mockito.spy(new WebserviceHookStub());
        PowerMockito.when(Util.getWebServiceHook()).thenReturn(webServiceHookStub);

        // service context
        Map<String, Object> requestContext = new HashMap<>();
        Stub_TMDMService mockStub = Mockito.mock(Stub_TMDMService.class);
        Mockito.when(mockStub.getRequestContext()).thenReturn(requestContext);

        TMDMService_Service mockService_service = PowerMockito.mock(TMDMService_Service.class);
        PowerMockito.whenNew(TMDMService_Service.class).withAnyArguments().thenReturn(mockService_service);
        PowerMockito.when(mockService_service.getTMDMPort()).thenReturn(mockStub);

        // call and verify
        TMDMService mdmService = Util.getMDMService(url, username, password, false);
        assertNotNull(mdmService);
        assertTrue(!requestContext.isEmpty());
        assertTrue(requestContext.containsKey(MessageContext.HTTP_REQUEST_HEADERS));
        Object obj = requestContext.get(MessageContext.HTTP_REQUEST_HEADERS);
        assertTrue(obj instanceof Map);
        Map<String, List<String>> headers = (Map<String, List<String>>) obj;
        assertTrue(headers.containsKey("t_stoken"));
        assertTrue(!headers.get("t_stoken").isEmpty());
    }

    static interface Stub_TMDMService extends TMDMService, BindingProvider {
        // empty
    }

    static class WebserviceHookStub implements IMDMWebServiceHook {

        @Override
        public void preRequestSendingHook(Map<String, Object> requestContext, String userName) {
            String studioToken = buildStudioToken(userName);

            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            List<String> values = Collections.singletonList(studioToken);
            headers.put(getTokenKey(), values);

            requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
        }

        @Override
        public String buildStudioToken(String username) {
            return "AE6D37D6FA60B30F";
        }

        @Override
        public String getTokenKey() {
            return "t_stoken";
        }
    }

}
