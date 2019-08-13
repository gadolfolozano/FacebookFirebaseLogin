package pe.com.gadolfolozano.firebasefacebooklogin.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import java.util.Calendar;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.BR;
import pe.com.gadolfolozano.firebasefacebooklogin.MainActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.R;
import pe.com.gadolfolozano.firebasefacebooklogin.base.BaseActivity;
import pe.com.gadolfolozano.firebasefacebooklogin.databinding.ActivityRegisterBinding;
import pe.com.gadolfolozano.firebasefacebooklogin.model.RegisterResponseModel;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @Inject
    RegisterViewModel viewModel;

    private ActivityRegisterBinding binding;
    private TextWatcher ageTextWatcher;

    @Override
    public int getBindingVariable() {
        return BR.registerViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ageTextWatcher = new AgeTextWatcher();
        binding = getViewDataBinding();
        binding.tvAge.addTextChangedListener(ageTextWatcher);
        binding.tvAge.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                hideKeyboard();
                showDatePicker();
                return true;
            }
            return false;
        });
        binding.tvBirthday.setOnClickListener(v -> showDatePicker());

        viewModel.getLoading().observe(this, this::observeLoading);
        viewModel.getShowError().observe(this, this::observeShowError);
        viewModel.getShouldPerformSave().observe(this, this::observeShouldPerformSave);
    }

    private void observeShouldPerformSave(Boolean shouldPerformSave) {
        if (shouldPerformSave != null && shouldPerformSave) {
            viewModel.performSaveData().observe(this, this::observeSaveData);
        }
    }

    private void observeSaveData(RegisterResponseModel registerResponseModel) {
        if (registerResponseModel != null) {
            viewModel.getLoading().setValue(false);
            if (registerResponseModel.isSucessfull()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                showAlert(
                        getString(R.string.text_alert_error_register_title),
                        registerResponseModel.getErrorMessage(),
                        getString(R.string.text_alert_error_register_button));
            }
        }
    }

    private void observeShowError(Boolean showError) {
        if (showError != null && showError) {
            showAlert(getString(R.string.text_alert_error_register_title),
                    getString(R.string.text_alert_error_register_message),
                    getString(R.string.text_alert_error_register_button));
        }
    }

    private void observeLoading(Boolean loading) {
        if (loading != null && loading) {
            showLoading();
        } else {
            hideLoading();
        }
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        DatePickerDialogFragment datePicker = DatePickerDialogFragment.newInstance(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        datePicker.setListener((view, year, month, dayOfMonth) -> viewModel.getBirthday().set(dayOfMonth + "/" + (month + 1) + "/" + year));
        datePicker.show(getSupportFragmentManager(), null);
    }

    private class AgeTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().isEmpty() && s.toString().charAt(0) == '0') {
                binding.tvAge.removeTextChangedListener(ageTextWatcher);
                String output = s.toString().substring(1);
                binding.tvAge.setText(output);
                binding.tvAge.addTextChangedListener(ageTextWatcher);
            }
        }
    }
}
