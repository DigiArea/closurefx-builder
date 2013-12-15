package com.digiarea.closure.model.validation;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.digiarea.closurefx.ResourceUtils;

public class MessageManager implements IMessageManager {

    private String ERROR_CHECK_BOX = "error-check-box";

    private String ERROR_TEXT_FIELD = "error-text-field";

    private String ERROR_COMBO_BOX = "error-combo-box";

    private String ERROR_TABLE_VIEW = "error-table-view";

    private String WARNING_CHECK_BOX = "warning-check-box";

    private String WARNING_TEXT_FIELD = "warning-text-field";

    private String WARNING_COMBO_BOX = "warning-combo-box";

    private String WARNING_TABLE_VIEW = "warning-table-view";

    private String DEFAULT_TABLE_VIEW = "default-table-view";

    private String ERROR_TITLE = "error-label";

    private String WARNING_TITLE = "warning-label";

    private ArrayList<MessageCategory> categories = new ArrayList<MessageCategory>();

    private ArrayList<MessageManager.Message> messages = new ArrayList<MessageManager.Message>();

    public MessageManager() {
    }

    public ArrayList<MessageCategory> getCategories() {
        return categories;
    }

    private Control getCategoryControl(String categoryKey) {
        for (MessageCategory category : categories) {
            if (category.getKey().equals(categoryKey)) {
                return category.getControl();
            }
        }
        return null;
    }

    private void updateResult(String categoryKey) {
        if (categoryKey != null) {
            StringBuilder builder = new StringBuilder();
            int errors = 0;
            int warnings = 0;
            for (MessageManager.Message message : messages) {
                if (message.getCategory().equals(categoryKey)) {
                    builder.append(message.getMessage() + "\n");
                    if (message.getMessageType() == IMessageProvider.ERROR) {
                        errors++;
                    } else if (message.getMessageType() == IMessageProvider.WARNING) {
                        warnings++;
                    }
                }
            }
            Control control = getCategoryControl(categoryKey);
            if (control != null && control instanceof Label) {
                Label resultLabel = (Label) control;
                if (errors != 0) {
                    resultLabel.setVisible(true);
                    resultLabel.setText(errors + " errors detected!");
                    resultLabel.getStyleClass().remove(WARNING_TITLE);
                    resultLabel.getStyleClass().add(ERROR_TITLE);
                    resultLabel.setGraphic(new ImageView(ResourceUtils.MARK_ERROR));
                    resultLabel.setTooltip(new Tooltip(builder.toString()));
                } else if (warnings != 0) {
                    resultLabel.setVisible(true);
                    resultLabel.setText(warnings + " warnings detected!");
                    resultLabel.getStyleClass().remove(ERROR_TITLE);
                    resultLabel.getStyleClass().add(WARNING_TITLE);
                    resultLabel.setGraphic(new ImageView(ResourceUtils.MARK_WARNING));
                    resultLabel.setTooltip(new Tooltip(builder.toString()));
                } else {
                    resultLabel.setVisible(false);
                    resultLabel.setText("");
                    resultLabel.getStyleClass().remove(WARNING_TITLE);
                    resultLabel.getStyleClass().remove(ERROR_TITLE);
                    resultLabel.setGraphic(null);
                    resultLabel.setTooltip(null);
                }
            }
        }
    }

    @Override
    public void addMessage(String categoryKey, Object key, String messageText, Object data, int type, Control control) {
        addMessage(new MessageManager.Message(categoryKey, key, messageText, type, data, control));
        updateResult(categoryKey);
    }

    private void addMessage(MessageManager.Message message) {
        if (!messages.contains(message)) {
            messages.add(message);
        }
        if (message.getControl() != null) {
            Control control = message.getControl();
            control.getStyleClass().remove(DEFAULT_TABLE_VIEW);
            control.setTooltip(new Tooltip(message.getMessage()));
            if (control instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) control;
                if (message.getMessageType() == IMessageProvider.ERROR) {
                    checkBox.getStyleClass().add(ERROR_CHECK_BOX);
                } else if (message.getMessageType() == IMessageProvider.WARNING) {
                    checkBox.getStyleClass().add(WARNING_CHECK_BOX);
                }
            } else if (control instanceof TextField) {
                TextField textField = (TextField) control;
                if (message.getMessageType() == IMessageProvider.ERROR) {
                    textField.getStyleClass().add(ERROR_TEXT_FIELD);
                } else if (message.getMessageType() == IMessageProvider.WARNING) {
                    textField.getStyleClass().add(WARNING_TEXT_FIELD);
                }
            } else if (control instanceof ComboBox<?>) {
                ComboBox<?> comboBox = (ComboBox<?>) control;
                if (message.getMessageType() == IMessageProvider.ERROR) {
                    comboBox.getStyleClass().add(ERROR_COMBO_BOX);
                } else if (message.getMessageType() == IMessageProvider.WARNING) {
                    comboBox.getStyleClass().add(WARNING_COMBO_BOX);
                }
            } else if (control instanceof TreeView<?> || control instanceof TableView<?> || control instanceof ListView<?>) {
                if (message.getMessageType() == IMessageProvider.ERROR) {
                    control.getStyleClass().add(ERROR_TABLE_VIEW);
                } else if (message.getMessageType() == IMessageProvider.WARNING) {
                    control.getStyleClass().add(WARNING_TABLE_VIEW);
                }
            }
        }
    }

    @Override
    public void removeMessage(Object key) {
    }

    @Override
    public void removeMessages() {
        for (MessageManager.Message message : messages) {
            removeMessage(message);
        }
        updateResult(null);
    }

    @Override
    public void removeMessage(Object key, Control control) {
    }

    @Override
    public void removeMessages(Control control) {
        Iterator<MessageManager.Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            IMessage message = iterator.next();
            if (message.getControl() != null) {
                if (message.getControl().equals(control)) {
                    clearControl(message);
                    iterator.remove();
                    updateResult(message.getCategory());
                }
            }
        }
    }

    private void removeMessage(IMessage message) {
    }

    @Override
    public void removeAllMessages() {
        Iterator<MessageManager.Message> iterator = messages.iterator();
        while (iterator.hasNext()) {
            clearControl(iterator.next());
            iterator.remove();
        }
        updateResult(null);
    }

    private void clearControl(IMessage message) {
        if (message.getControl() != null) {
            Control control = message.getControl();
            control.setTooltip(null);
            control.getStyleClass().removeAll(ERROR_CHECK_BOX, WARNING_CHECK_BOX, ERROR_TEXT_FIELD, WARNING_TEXT_FIELD, ERROR_COMBO_BOX, WARNING_COMBO_BOX, ERROR_TABLE_VIEW, WARNING_TABLE_VIEW);
            control.getStyleClass().add(DEFAULT_TABLE_VIEW);
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void setAutoUpdate(boolean enabled) {
    }

    @Override
    public boolean isAutoUpdate() {
        return false;
    }

    @Override
    public String createSummary(IMessage[] messages) {
        return null;
    }

    static class Message implements IMessage {

        String category;

        Control control;

        Object data;

        Object key;

        String message;

        int type;

        String prefix;

        public Message(String category, Object key, String message, int type, Object data) {
            this(category, key, message, type, data, null);
        }

        public Message(String category, Object key, String message, int type, Object data, Control control) {
            this.category = category;
            this.key = key;
            this.message = message;
            this.type = type;
            this.data = data;
            this.control = control;
        }

        public Image getDecorator() {
            if (type == IMessageProvider.ERROR) {
                return ResourceUtils.DECORATOR_ERROR;
            } else if (type == IMessageProvider.WARNING) {
                return ResourceUtils.DECORATOR_WARNING;
            } else if (type == IMessageProvider.INFORMATION) {
                return ResourceUtils.DECORATOR_INFO;
            }
            return null;
        }

        public Object getKey() {
            return key;
        }

        public String getMessage() {
            return message;
        }

        public int getMessageType() {
            return type;
        }

        public Control getControl() {
            return control;
        }

        public Object getData() {
            return data;
        }

        public String getPrefix() {
            return prefix;
        }

        @Override
        public String getCategory() {
            return category;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof com.digiarea.closure.model.validation.MessageManager.Message)) return false;
            com.digiarea.closure.model.validation.MessageManager.Message msg = (com.digiarea.closure.model.validation.MessageManager.Message) obj;
            return (msg.getPrefix() == null ? getPrefix() == null : msg.getPrefix().equals(getPrefix())) && (msg.getControl() == null ? getControl() == null : msg.getControl().equals(getControl())) && (msg.getMessageType() == getMessageType()) && (msg.getMessage() == null ? getMessage() == null : msg.getMessage().equals(getMessage())) && msg.getKey().equals(getKey());
        }

        @Override
        public void setControl(Control control) {
            this.control = control;
        }

    }

}
