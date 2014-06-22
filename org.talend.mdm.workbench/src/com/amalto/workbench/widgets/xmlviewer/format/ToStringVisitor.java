/*****************************************************************************
 * This file is part of Rinzo
 * 
 * Author: Claudio Cancinos WWW: https://sourceforge.net/projects/editorxml Copyright (C): 2008, Claudio Cancinos
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with this program; If not, see
 * <http://www.gnu.org/licenses/>
 ****************************************************************************/
package com.amalto.workbench.widgets.xmlviewer.format;

import java.util.StringTokenizer;

import com.amalto.workbench.widgets.xmlviewer.model.XMLNode;
import com.amalto.workbench.widgets.xmlviewer.utils.FileUtils;
import com.amalto.workbench.widgets.xmlviewer.utils.XMLViewUtils;


public class ToStringVisitor implements StringGeneratorVisitor {
	private StringBuffer buffer = new StringBuffer();
	private int identation = 0;
	private boolean addLineSeparator = true;
	
	public boolean visitStart(XMLNode node) {
		if(this.identation >0) {
			this.addLineSeparator();
		}
		this.addIdentation();
		this.addLine(node.getContent());
		this.identation++;
		
		if(!node.hasChildren()) {
			this.addLineSeparator = false;
		}
		return true;
	}

	public boolean visitEnd(XMLNode node) {
		this.identation--;
		if(this.addLineSeparator) {
			this.addLineSeparator();
			this.addIdentation();
		} else {
			this.addLineSeparator = true;
		}
		this.addLine(node.getCorrespondingNode().getContent());
		
		return true;
	}
	public boolean visitChild(XMLNode node) {
		String content = node.getContent();
        String trimmedContent = ""; //$NON-NLS-1$
		if(node.isTextTag() || node.isCdata()) {
			trimmedContent = content.trim();
			if(XMLViewUtils.isEmpty(trimmedContent)) {
				this.addBlankLines(content);
			}
		}
		if(trimmedContent.length() > 0) {
			boolean singleChild = node.getParent().getChildren().size() == 1;
			if(node.isCommentTag() || node.isEmptyTag() || trimmedContent.length() > 60 || !singleChild) {
				this.addLineSeparator();
				this.addIdentation();
			} else {
				if(singleChild) {
					this.addLineSeparator = false;
				}
			}
			this.addLine(content);
		}
		
		return true;
	}

	private void addBlankLines(String content) {
		int index = content.indexOf(FileUtils.LINE_SEPARATOR);
		index = content.indexOf(FileUtils.LINE_SEPARATOR, index + 1);
		while(index >= 0) {
			this.addLineSeparator();
			index = content.indexOf(FileUtils.LINE_SEPARATOR, index + 1);
		}
	}

	private void addIdentation() {
		for (int i = 0; i < identation; i++) {
			this.buffer.append(this.getIndentToken());
		}
	}

	private void addLineSeparator() {
		this.buffer.append(FileUtils.LINE_SEPARATOR);
	}

	public void reset() {
		this.buffer = new StringBuffer();
	}

	public String getString() {
		return this.buffer.toString();
	}
	
	private void addLine(String line) {
		if(line.length() <= this.getMaxLineLength()) {
			this.buffer.append(line);
		} else {
            StringTokenizer tokenizer = new StringTokenizer(line, " "); //$NON-NLS-1$
			StringBuffer currentLine = new StringBuffer();
			StringBuffer finalLine = new StringBuffer();
			while(tokenizer.hasMoreTokens()) {
				while(tokenizer.hasMoreTokens() && currentLine.length() <= this.getMaxLineLength()) {
					String nextToken = tokenizer.nextToken().trim();
                    currentLine.append((currentLine.toString().trim().length() == 0) ? nextToken : " " + nextToken); //$NON-NLS-1$
				}
				if(currentLine.length() != 0) {
					if(tokenizer.hasMoreTokens()) {
						currentLine.append(FileUtils.LINE_SEPARATOR);
					}
					finalLine.append(currentLine.toString());
					currentLine = new StringBuffer();
					for (int i = 0; i < identation + 1; i++) {
						currentLine.append(this.getIndentToken());
					}
				}
			}
			this.buffer.append(finalLine.toString());
		}
	}

	private int getMaxLineLength() {
        return 1000;
	}

	private String getIndentToken() {
        return "  "; //$NON-NLS-1$
	}
}
