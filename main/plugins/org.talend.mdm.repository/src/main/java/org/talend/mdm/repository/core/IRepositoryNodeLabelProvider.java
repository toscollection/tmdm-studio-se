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
package org.talend.mdm.repository.core;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.talend.core.model.repository.ERepositoryObjectType;

/**
 * DOC hbhong class global comment. Detailled comment <br/>
 *
 */
public interface IRepositoryNodeLabelProvider {

    public String getCategoryLabel(ERepositoryObjectType type);

    public String getText(Object element);

    public Image getImage(Object element);

    public String getDescription(Object anElement);

    public Font getFont(Object element);
    //

}
