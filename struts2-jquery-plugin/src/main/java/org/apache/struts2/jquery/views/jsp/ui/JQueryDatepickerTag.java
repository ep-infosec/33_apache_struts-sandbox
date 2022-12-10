/*
 * $Id$
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.jquery.views.jsp.ui;

import com.opensymphony.xwork2.util.ValueStack;
import org.apache.struts2.components.Component;
import org.apache.struts2.jquery.components.JQueryDatepicker;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JQueryDatepickerTag extends JQueryTextFieldTag {
    private String displayFormat;
    private String imageUrl;
    private String imageTooltip;
    private String options;
    private String changeYear;
    private String changeMonth;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        return new JQueryDatepicker(stack, req, res);
    }

    protected void populateParams() {
        super.populateParams();
        JQueryDatepicker picker = ((JQueryDatepicker) component);
        picker.setDisplayFormat(displayFormat);
        picker.setImageTooltip(imageTooltip);
        picker.setImageUrl(imageUrl);
        picker.setOptions(options);
        picker.setChangeMonth(changeMonth);
        picker.setChangeYear(changeYear);
    }

    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
    }

    public void setImageTooltip(String imageTooltip) {
        this.imageTooltip = imageTooltip;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public void setChangeYear(String changeYear) {
        this.changeYear = changeYear;
    }

    public void setChangeMonth(String changeMonth) {
        this.changeMonth = changeMonth;
    }
}
