/*
 * Generated by XDoclet - Do not edit!
 */
package com.amalto.core.plugin.base.fixedlengthparser.ejb.local;

/**
 * Local home interface for FixedLengthParserTransformerPlugin.
 * @xdoclet-generated at 16-07-09
 * @copyright The XDoclet Team
 * @author XDoclet
 * @version ${version}
 */
public interface FixedLengthParserTransformerPluginLocalHome
   extends javax.ejb.EJBLocalHome
{
   public static final String COMP_NAME="java:comp/env/ejb/FixedLengthParserTransformerPluginLocal";
   public static final String JNDI_NAME="amalto/local/transformer/plugin/fixedlengthparser";

   public com.amalto.core.plugin.base.fixedlengthparser.ejb.local.FixedLengthParserTransformerPluginLocal create()
      throws javax.ejb.CreateException;

}
