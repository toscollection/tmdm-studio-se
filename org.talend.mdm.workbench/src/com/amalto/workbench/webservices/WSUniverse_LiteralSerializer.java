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

public class WSUniverse_LiteralSerializer extends LiteralObjectSerializerBase implements Initializable  {
    private static final QName ns1_name_QNAME = new QName("", "name");
    private static final QName ns3_string_TYPE_QNAME = SchemaConstants.QNAME_TYPE_STRING;
    private CombinedSerializer ns3_myns3_string__java_lang_String_String_Serializer;
    private static final QName ns1_description_QNAME = new QName("", "description");
    private static final QName ns1_xtentisObjectsRevisionIDs_QNAME = new QName("", "xtentisObjectsRevisionIDs");
    private static final QName ns2_WSUniverse$2d$xtentisObjectsRevisionIDs_TYPE_QNAME = new QName("urn-com-amalto-xtentis-webservice", "WSUniverse-xtentisObjectsRevisionIDs");
    private CombinedSerializer ns2_myWSUniverseXtentisObjectsRevisionIDs_LiteralSerializer;
    private static final QName ns1_defaultItemsRevisionID_QNAME = new QName("", "defaultItemsRevisionID");
    private static final QName ns1_itemsRevisionIDs_QNAME = new QName("", "itemsRevisionIDs");
    private static final QName ns2_WSUniverse$2d$itemsRevisionIDs_TYPE_QNAME = new QName("urn-com-amalto-xtentis-webservice", "WSUniverse-itemsRevisionIDs");
    private CombinedSerializer ns2_myWSUniverseItemsRevisionIDs_LiteralSerializer;
    
    public WSUniverse_LiteralSerializer(QName type, String encodingStyle) {
        this(type, encodingStyle, false);
    }
    
    public WSUniverse_LiteralSerializer(QName type, String encodingStyle, boolean encodeType) {
        super(type, true, encodingStyle, encodeType);
    }
    
    public void initialize(InternalTypeMappingRegistry registry) throws Exception {
        ns3_myns3_string__java_lang_String_String_Serializer = (CombinedSerializer)registry.getSerializer("", java.lang.String.class, ns3_string_TYPE_QNAME);
        ns2_myWSUniverseXtentisObjectsRevisionIDs_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.amalto.workbench.webservices.WSUniverseXtentisObjectsRevisionIDs.class, ns2_WSUniverse$2d$xtentisObjectsRevisionIDs_TYPE_QNAME);
        ns2_myWSUniverseItemsRevisionIDs_LiteralSerializer = (CombinedSerializer)registry.getSerializer("", com.amalto.workbench.webservices.WSUniverseItemsRevisionIDs.class, ns2_WSUniverse$2d$itemsRevisionIDs_TYPE_QNAME);
    }
    
    public Object doDeserialize(XMLReader reader,
        SOAPDeserializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSUniverse instance = new com.amalto.workbench.webservices.WSUniverse();
        Object member=null;
        QName elementName;
        List values;
        Object value;
        
        reader.nextElementContent();
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_name_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_name_QNAME, reader, context);
                if (member == null) {
                    throw new DeserializationException("literal.unexpectedNull");
                }
                instance.setName((java.lang.String)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_name_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_description_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_description_QNAME, reader, context);
                instance.setDescription((java.lang.String)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_description_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_xtentisObjectsRevisionIDs_QNAME))) {
            values = new ArrayList();
            for(;;) {
                elementName = reader.getName();
                if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_xtentisObjectsRevisionIDs_QNAME))) {
                    value = ns2_myWSUniverseXtentisObjectsRevisionIDs_LiteralSerializer.deserialize(ns1_xtentisObjectsRevisionIDs_QNAME, reader, context);
                    if (value == null) {
                        throw new DeserializationException("literal.unexpectedNull");
                    }
                    values.add(value);
                    reader.nextElementContent();
                } else {
                    break;
                }
            }
            member = new com.amalto.workbench.webservices.WSUniverseXtentisObjectsRevisionIDs[values.size()];
            member = values.toArray((Object[]) member);
            instance.setXtentisObjectsRevisionIDs((com.amalto.workbench.webservices.WSUniverseXtentisObjectsRevisionIDs[])member);
        }
        else {
            instance.setXtentisObjectsRevisionIDs(new com.amalto.workbench.webservices.WSUniverseXtentisObjectsRevisionIDs[0]);
        }
        elementName = reader.getName();
        if (reader.getState() == XMLReader.START) {
            if (elementName.equals(ns1_defaultItemsRevisionID_QNAME)) {
                member = ns3_myns3_string__java_lang_String_String_Serializer.deserialize(ns1_defaultItemsRevisionID_QNAME, reader, context);
                instance.setDefaultItemsRevisionID((java.lang.String)member);
                reader.nextElementContent();
            } else {
                throw new DeserializationException("literal.unexpectedElementName", new Object[] { ns1_defaultItemsRevisionID_QNAME, reader.getName() });
            }
        }
        else {
            throw new DeserializationException("literal.expectedElementName", reader.getName().toString());
        }
        elementName = reader.getName();
        if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_itemsRevisionIDs_QNAME))) {
            values = new ArrayList();
            for(;;) {
                elementName = reader.getName();
                if ((reader.getState() == XMLReader.START) && (elementName.equals(ns1_itemsRevisionIDs_QNAME))) {
                    value = ns2_myWSUniverseItemsRevisionIDs_LiteralSerializer.deserialize(ns1_itemsRevisionIDs_QNAME, reader, context);
                    if (value == null) {
                        throw new DeserializationException("literal.unexpectedNull");
                    }
                    values.add(value);
                    reader.nextElementContent();
                } else {
                    break;
                }
            }
            member = new com.amalto.workbench.webservices.WSUniverseItemsRevisionIDs[values.size()];
            member = values.toArray((Object[]) member);
            instance.setItemsRevisionIDs((com.amalto.workbench.webservices.WSUniverseItemsRevisionIDs[])member);
        }
        else {
            instance.setItemsRevisionIDs(new com.amalto.workbench.webservices.WSUniverseItemsRevisionIDs[0]);
        }
        
        XMLReaderUtil.verifyReaderState(reader, XMLReader.END);
        return (Object)instance;
    }
    
    public void doSerializeAttributes(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSUniverse instance = (com.amalto.workbench.webservices.WSUniverse)obj;
        
    }
    public void doSerialize(Object obj, XMLWriter writer, SOAPSerializationContext context) throws Exception {
        com.amalto.workbench.webservices.WSUniverse instance = (com.amalto.workbench.webservices.WSUniverse)obj;
        
        if (instance.getName() == null) {
            throw new SerializationException("literal.unexpectedNull");
        }
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getName(), ns1_name_QNAME, null, writer, context);
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getDescription(), ns1_description_QNAME, null, writer, context);
        if (instance.getXtentisObjectsRevisionIDs() != null) {
            for (int i = 0; i < instance.getXtentisObjectsRevisionIDs().length; ++i) {
                ns2_myWSUniverseXtentisObjectsRevisionIDs_LiteralSerializer.serialize(instance.getXtentisObjectsRevisionIDs()[i], ns1_xtentisObjectsRevisionIDs_QNAME, null, writer, context);
            }
        }
        ns3_myns3_string__java_lang_String_String_Serializer.serialize(instance.getDefaultItemsRevisionID(), ns1_defaultItemsRevisionID_QNAME, null, writer, context);
        if (instance.getItemsRevisionIDs() != null) {
            for (int i = 0; i < instance.getItemsRevisionIDs().length; ++i) {
                ns2_myWSUniverseItemsRevisionIDs_LiteralSerializer.serialize(instance.getItemsRevisionIDs()[i], ns1_itemsRevisionIDs_QNAME, null, writer, context);
            }
        }
    }
}
