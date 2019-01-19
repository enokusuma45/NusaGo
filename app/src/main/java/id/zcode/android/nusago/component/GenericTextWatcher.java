package id.zcode.android.nusago.component;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class GenericTextWatcher implements TextWatcher {
    private EditText current, next;
    private List<EditText> editTexts = new ArrayList<>();
    private Context context;

    public GenericTextWatcher(Context context, EditText current, List<EditText> editTexts) {
        this.context = context;
        this.editTexts = editTexts;
        this.current = current;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        if (text.isEmpty()) return;
        int id = current.getId();
        int i = 0;
        for (; i < editTexts.size(); i++) {
            if (editTexts.get(i).getId() == id) break;
        }
        next = ++i == editTexts.size() ? null : editTexts.get(i);
        if (text.length() == 1 && next != null) {
            next.requestFocus();
        }

        if (next == null) {
            View view = ((Activity) context).getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }
}

