// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation （1.1.2_01，编译版 R40）
// Generated source version: 1.1.2

package com.amalto.workbench.webservices;

import com.sun.xml.rpc.encoding.*;
import com.sun.xml.rpc.encoding.xsd.XSDConstants;
import com.sun.xml.rpc.encoding.literal.*;
import com.sun.xml.rpc.encoding.literal.DetailFragmentDeserializer;
import com.sun.xml.rpc.encoding.simpletype.*;
import com.sun.xml.rpc.encoding.soap.SOAPConstants;
import com.sun.xml.rpc.encoding.soap.SOAP12Constants;
import com.sun.xml.rpc.streaming.*;
import com.sun.xml.rpc.wsdl.document.schema.SchemaConstants;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.ArrayList;

public class WSDeleteItemWithReport_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable  {
    private static final QName ns1_wsItemPK_QNAME = new QName("", "wsItemPK");
    private static final QName ns2_WSItemPK_TYPE_QNAME = new QName("urn-com-amalto-xtentis-webservice", "WSItemPK");
    private CombinedSerializer ns2_myWSItemPK_LiteralSerializer;
    private static final QName ns1_source_QNAME = new QName("", "source");
    private static final QName ns3_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns3_myns3_string__java_lang_String_String_Serializer;
    private static final QName ns1_operateType_QNAME = new QName("", "operateType");
    private static final QName ns1_updatePath_QNAME = new QName("", "updatePath");
    private static final QName ns1_user_QNAME = new QName("", "user");
    private static final QName ns1_invokeBeforeSaving_QNAME = new QName("", "invokeBeforeSaving");
    private static final QName ns3_boolean_TYPE_QNAME = SchemaConstants.QNAME_TYPE_BOOLEAN;
    private CombinedSerializer ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer;
    private static final QName ns1_pushToUpdateReport_QNAME = new QName("", "pushToUpdateReport");
    private static final QName ns1_override_QNAME = new QName("", "override");
    
    public WSDeleteItemWithReport_LiteralSerializer(QName type, String encodingStyle) {
        this(type, encodingStyle, false);
    }
    
    public WSDeleteItemWithReport_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
        super(type, true, encodingStyle, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        ns2_myWSItemPK_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.amalto.workbench.webservices.WSItemPK.class, ns2_WSItemPK_TYPE_QNAME);
        ns3_myns3_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns3_string_TYPE_QNAME);
        ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.Boolean.class, ns3_boolean_TYPE_QNAME);
    }
    
    public Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSDeleteItemWithReport instance = new com.amalto.workbench.webservices.WSDeleteItemWithReport();
        Object member=null;
        QName elementName;
        List values;
        Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_wsItemPK_QNAME)) {
                member = ns2_myWSItemPK_LiteralSerializer.deserialize(ns1_wsItemPK_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setWsItemPK((com.amalto.workbench.webservices.WSItemPK)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_wsItemPK_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_source_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_source_QNAME, reader, context);
                instance.setSource((java.lang.String)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_operateType_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_operateType_QNAME, reader, context);
                instance.setOperateType((java.lang.String)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_updatePath_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_updatePath_QNAME, reader, context);
                instance.setUpdatePath((java.lang.String)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_user_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_user_QNAME, reader, context);
                instance.setUser((java.lang.String)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_invokeBeforeSaving_QNAME)) {
                member = ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer.deserialize(ns1_invokeBeforeSaving_QNAME, reader, context);
                instance.setInvokeBeforeSaving((java.lang.Boolean)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_pushToUpdateReport_QNAME)) {
                member = ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer.deserialize(ns1_pushToUpdateReport_QNAME, reader, context);
                instance.setPushToUpdateReport((java.lang.Boolean)member);
                reader.nextElementContent();
            }
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_override_QNAME)) {
                member = ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer.deserialize(ns1_override_QNAME, reader, context);
                instance.setOverride((java.lang.Boolean)member);
                reader.nextElementContent();
            }
        }
        
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (Object)instance;
    }
    
    public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSDeleteItemWithReport instance = (com.amalto.workbench.webservices.WSDeleteItemWithReport)obj;
        
    }
    public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSDeleteItemWithReport instance = (com.amalto.workbench.webservices.WSDeleteItemWithReport)obj;
        
        if (instance.getWsItemPK() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns2_myWSItemPK_LiteralSerializer.serialize(instance.getWsItemPK(), ns1_wsItemPK_QNAME, null, writer, context);
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getSource(), ns1_source_QNAME, null, writer, context);
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getOperateType(), ns1_operateType_QNAME, null, writer, context);
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getUpdatePath(), ns1_updatePath_QNAME, null, writer, context);
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getUser(), ns1_user_QNAME, null, writer, context);
        ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer.serialize(instance.getInvokeBeforeSaving(), ns1_invokeBeforeSaving_QNAME, null, writer, context);
        ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer.serialize(instance.getPushToUpdateReport(), ns1_pushToUpdateReport_QNAME, null, writer, context);
        ns3_myns3__boolean__java_lang_Boolean_Boolean_Serializer.serialize(instance.getOverride(), ns1_override_QNAME, null, writer, context);
    }
}
