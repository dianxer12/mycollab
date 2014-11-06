/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.server.Resource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
public class AttachmentPreviewView extends AbsoluteLayout {

	private static final long serialVersionUID = -6489047489795500038L;

	private Resource currentResource;
	private CssLayout imgWrap;
	private Image previewImage;

	public AttachmentPreviewView() {
		imgWrap = new CssLayout();
		imgWrap.setStyleName("image-wrap");
		imgWrap.setSizeFull();

		this.setStyleName("attachment-preview-view");
		this.setSizeFull();
		this.addComponent(imgWrap, "top: 0px left: 0px; z-index: 0;");

		Button backBtn = new Button(
				AppContext.getMessage(GenericI18Enum.M_BUTTON_BACK),
				new Button.ClickListener() {

					private static final long serialVersionUID = -5974915704928855021L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						EventBusFactory.getInstance().post(
								new ShellEvent.NavigateBack(this, null));
					}
				});
		backBtn.setStyleName("back-btn");

		this.addComponent(backBtn, "top: 15px; left: 15px; z-index: 1;");

		previewImage = new Image();
		imgWrap.addComponent(previewImage);
	}

	public AttachmentPreviewView(Resource previewImage) {
		this();
		setResource(previewImage);
	}

	public void setResource(Resource previewImage) {
		this.currentResource = previewImage;
		this.previewImage.setSource(currentResource);
	}

	public Resource getResource() {
		return this.currentResource;
	}
}