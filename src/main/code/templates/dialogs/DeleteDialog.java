package code.templates.dialogs;

import com.vaadin.flow.component.confirmdialog.ConfirmDialog;

public class DeleteDialog extends ConfirmDialog {

	private static final long serialVersionUID = 9009440682810832934L;
	
	public DeleteDialog(String text, String cancelButtonText, String confirmButtonText) {
		this.initDialog(text, cancelButtonText, confirmButtonText);
	}
	
	public DeleteDialog(String text) {
		this.initDialog(text, "Cancel", "Confirm");
	}
	
	public void initDialog(String text, String cancelButtonText, String confirmButtonText) {
		setCloseOnEsc(false);
		setCancelable(true);
		setCancelText(cancelButtonText);
		setConfirmText(confirmButtonText);
		setText(text);
	}
}
