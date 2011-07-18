package unbbayes.gui.table;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Class responsible for replacing the old value by the new value typed instead of appending it.
 * However, if the focus is gained by something different than by typing a new value, then it 
 * just selects all the text inside the text field component.
 */
public class ReplaceTextCellEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;

	protected JTextField textField = new JTextField();
	protected String previousValue = "";

	public ReplaceTextCellEditor() {
		super(new JTextField());
		this.textField = (JTextField) this.editorComponent;
		
		this.textField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				// New value that the text field has.
				String text = textField.getText();
				// If it is the same, just select it all.
				if (previousValue.equals(text)) {
					cancelCellEditing();
//					textField.selectAll();
				// If it is different than what it had before, than just replace the 
				// previous value by the new value typed (text - previousValue). 
				// Because text = previousValue + newValueTyped.
				} else if (text.length() > 0 && previousValue.length() < text.length()) {
					textField.setText(text.substring(previousValue.length()));
				}
			}
		});
		
	}

	public void setValue(Object value) {
		textField.setText((value != null) ? value.toString() : "");
	}

	public Object getCellEditorValue() {
		return textField.getText();
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		if (isSelected == false) {
			return null;
		}

		// Used to control what to do when the text filed gains focus (select all or replace text).
		this.previousValue = value.toString();

		this.textField.setText(this.previousValue);
		
		

		return this.textField;
	}

}
