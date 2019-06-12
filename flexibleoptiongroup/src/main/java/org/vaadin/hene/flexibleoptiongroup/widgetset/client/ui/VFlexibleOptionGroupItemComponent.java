package org.vaadin.hene.flexibleoptiongroup.widgetset.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * @author Henri Kerola / Vaadin Ltd
 *
 */
public class VFlexibleOptionGroupItemComponent extends Composite
implements ClickHandler, Focusable
{
    public static final String CLASSNAME = "v-flexibleoptiongroupitemcomponent";

    protected SimplePanel panel;
    protected CheckBox checkbox;

    private String ownerId;
    private boolean multiSelect;

    private ComponentCheckedListener checkedListener;

    public VFlexibleOptionGroupItemComponent() {
        panel = new SimplePanel();
        initWidget(panel);

        setStyleName(CLASSNAME);
    }

    @Override
    public void onClick(ClickEvent event) {
        updateChecked(checkbox.getValue());
        setFocus(true);
    }

    public void setMultiSelect(boolean multiselect) {
        if (checkbox != null && this.multiSelect == multiselect) {
            return;
        }

        this.multiSelect = multiselect;
        if (multiselect) {
            checkbox = new CheckBox();
            checkbox.addStyleName("v-checkbox");
        } else {
            checkbox = new RadioButton(ownerId);
            checkbox.setStyleName("v-radiobutton");
        }

        checkbox.addClickHandler(this);
        panel.setWidget(checkbox);
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        if (checkbox instanceof RadioButton) {
            checkbox.setName(ownerId);
        }
    }

    public void setEnabled(boolean enabled) {
        if (checkbox != null) {
            checkbox.setEnabled(enabled);
        }
    }

    public void setSelected(boolean selected) {
        if (checkbox != null) {
            checkbox.setValue(selected);
        }
    }

    public interface ComponentCheckedListener {
        void checked(boolean checked);
    }

    protected void updateChecked(boolean checked) {
        if (checkedListener != null) {
            checkedListener.checked(checked);
        }
    }

    public void setCheckedListener(ComponentCheckedListener checkedListener) {
        this.checkedListener = checkedListener;
    }

    @Override
    public int getTabIndex()
    {
        return checkbox == null ? 0 : checkbox.getTabIndex();
    }

    @Override
    public void setTabIndex(int index)
    {
        if (checkbox != null)
        {
            checkbox.setTabIndex(index);
        }
    }

    @Override
    public void setAccessKey(char key)
    {
        if (checkbox != null)
        {
            checkbox.setAccessKey(key);
        }
    }

    @Override
    public void setFocus(boolean focused)
    {
        if (checkbox != null)
        {
            checkbox.setFocus(focused);
        }
    }
}
