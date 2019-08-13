package pe.com.gadolfolozano.firebasefacebooklogin.ui.register;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DatePickerDialogFragment extends DialogFragment {
    private static final String ARGUMENT_YEAR = "ARGUMENT_YEAR";
    private static final String ARGUMENT_MONTH = "ARGUMENT_MONTH";
    private static final String ARGUMENT_DAY = "ARGUMENT_DAY";
    private DatePickerDialog.OnDateSetListener listener;

    private int year;
    private int month;
    private int dayOfMonth;

    public static DatePickerDialogFragment newInstance(final int year, final int month, final int dayOfMonth) {
        final DatePickerDialogFragment df = new DatePickerDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_YEAR, year);
        args.putInt(ARGUMENT_MONTH, month);
        args.putInt(ARGUMENT_DAY, dayOfMonth);
        df.setArguments(args);
        return df;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            year = args.getInt(ARGUMENT_YEAR);
            month = args.getInt(ARGUMENT_MONTH);
            dayOfMonth = args.getInt(ARGUMENT_DAY);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new DatePickerDialog(getContext(), this.listener, this.year, this.month, this.dayOfMonth);
    }

    public void setListener(final DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }
}
