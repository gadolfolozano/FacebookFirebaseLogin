package pe.com.gadolfolozano.firebasefacebooklogin.ui.register;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import pe.com.gadolfolozano.firebasefacebooklogin.data.DataManager;
import pe.com.gadolfolozano.firebasefacebooklogin.model.RegisterResponseModel;

public class RegisterViewModel extends ViewModel {
    private DataManager dataManager;
    private ObservableField<String> name;
    private ObservableField<String> surname;
    private ObservableField<String> age;
    private ObservableField<String> birthday;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<Boolean> showError;
    private MutableLiveData<Boolean> shouldPerformSave;

    @Inject
    public RegisterViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
        this.name = new ObservableField<>();
        this.surname = new ObservableField<>();
        this.age = new ObservableField<>();
        this.birthday = new ObservableField<>();
        this.loading = new MutableLiveData<>();
        this.loading.setValue(false);
        this.showError = new MutableLiveData<>();
        this.showError.setValue(false);
        this.shouldPerformSave = new MutableLiveData<>();
        this.shouldPerformSave.setValue(false);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<String> getSurname() {
        return surname;
    }

    public void setSurname(ObservableField<String> surname) {
        this.surname = surname;
    }

    public ObservableField<String> getAge() {
        return age;
    }

    public void setAge(ObservableField<String> age) {
        this.age = age;
    }

    public ObservableField<String> getBirthday() {
        return birthday;
    }

    public void setBirthday(ObservableField<String> birthday) {
        this.birthday = birthday;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public void setLoading(MutableLiveData<Boolean> loading) {
        this.loading = loading;
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }

    public void setShowError(MutableLiveData<Boolean> showError) {
        this.showError = showError;
    }

    public MutableLiveData<Boolean> getShouldPerformSave() {
        return shouldPerformSave;
    }

    public void setShouldPerformSave(MutableLiveData<Boolean> shouldPerformSave) {
        this.shouldPerformSave = shouldPerformSave;
    }

    public void onButtonClicked() {
        boolean inputsAreValid = name.get() != null && !name.get().isEmpty()
                && surname.get() != null && !surname.get().isEmpty()
                && age.get() != null && !age.get().isEmpty()
                && birthday.get() != null && !birthday.get().isEmpty();

        if (inputsAreValid) {
            shouldPerformSave.setValue(true);
        } else {
            showError.setValue(true);
        }
    }

    public LiveData<RegisterResponseModel> performSaveData() {
        loading.setValue(true);
        return dataManager.performSaveData(name.get(), surname.get(), age.get(), birthday.get());
    }
}
