// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation （1.1.2_01，编译版 R40）
// Generated source version: 1.1.2

package com.amalto.workbench.webservices;


public class WSQuickSearch {
    protected com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK;
    protected com.amalto.workbench.webservices.WSViewPK wsViewPK;
    protected java.lang.String searchedValue;
    protected int maxItems;
    protected int skip;
    protected int spellTreshold;
    protected boolean matchAllWords;
    protected java.lang.String orderBy;
    protected java.lang.String direction;
    
    public WSQuickSearch() {
    }
    
    public WSQuickSearch(com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK, com.amalto.workbench.webservices.WSViewPK wsViewPK, java.lang.String searchedValue, int maxItems, int skip, int spellTreshold, boolean matchAllWords, java.lang.String orderBy, java.lang.String direction) {
        this.wsDataClusterPK = wsDataClusterPK;
        this.wsViewPK = wsViewPK;
        this.searchedValue = searchedValue;
        this.maxItems = maxItems;
        this.skip = skip;
        this.spellTreshold = spellTreshold;
        this.matchAllWords = matchAllWords;
        this.orderBy = orderBy;
        this.direction = direction;
    }
    
    public com.amalto.workbench.webservices.WSDataClusterPK getWsDataClusterPK() {
        return wsDataClusterPK;
    }
    
    public void setWsDataClusterPK(com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK) {
        this.wsDataClusterPK = wsDataClusterPK;
    }
    
    public com.amalto.workbench.webservices.WSViewPK getWsViewPK() {
        return wsViewPK;
    }
    
    public void setWsViewPK(com.amalto.workbench.webservices.WSViewPK wsViewPK) {
        this.wsViewPK = wsViewPK;
    }
    
    public java.lang.String getSearchedValue() {
        return searchedValue;
    }
    
    public void setSearchedValue(java.lang.String searchedValue) {
        this.searchedValue = searchedValue;
    }
    
    public int getMaxItems() {
        return maxItems;
    }
    
    public void setMaxItems(int maxItems) {
        this.maxItems = maxItems;
    }
    
    public int getSkip() {
        return skip;
    }
    
    public void setSkip(int skip) {
        this.skip = skip;
    }
    
    public int getSpellTreshold() {
        return spellTreshold;
    }
    
    public void setSpellTreshold(int spellTreshold) {
        this.spellTreshold = spellTreshold;
    }
    
    public boolean isMatchAllWords() {
        return matchAllWords;
    }
    
    public void setMatchAllWords(boolean matchAllWords) {
        this.matchAllWords = matchAllWords;
    }
    
    public java.lang.String getOrderBy() {
        return orderBy;
    }
    
    public void setOrderBy(java.lang.String orderBy) {
        this.orderBy = orderBy;
    }
    
    public java.lang.String getDirection() {
        return direction;
    }
    
    public void setDirection(java.lang.String direction) {
        this.direction = direction;
    }
}