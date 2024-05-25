package mft.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Customer;
import mft.model.tools.Validator;
import mft.view.WindowsManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class ProfileController implements Initializable {
    @FXML
    private TextField idTxt, nameTxt, familyTxt, searchFamilyTxt;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<String> cityCmb;

    @FXML
    private RadioButton maleRdo, femaleRdo;

    @FXML
    private ToggleGroup genderToggle;

    @FXML
    private CheckBox algoChk, seChk, eeChk;

    @FXML
    private Button saveBtn, editBtn, removeBtn;

    @FXML
    private TableView<Customer> personTbl;

    @FXML
    private TableColumn<Customer, Integer> idCol;

    @FXML
    private TableColumn<Customer, String> firstNameCol, lastNameCol;

    @FXML
    private MenuItem closeMnu, newMnu, aboutMnu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("App Started");
        for (City value : City.values()) {
            cityCmb.getItems().add(value.name());
        }

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Load Error\n" + e.getMessage());
            alert.show();
        }

        newMnu.setOnAction(event -> {
            try {
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        closeMnu.setOnAction((event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure ?");
            if (alert.showAndWait().get().equals(ButtonType.OK)) {
                Platform.exit();
            }
            log.info("App Closed");
        }));

        aboutMnu.setOnAction((event) -> {
            try {
                WindowsManager.showAboutForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Load Error\n" + e.getMessage());
                alert.show();
            }
        });

        saveBtn.setOnAction(event -> {
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();

                Customer customer = Customer
                        .builder()
                        .name(Validator.nameValidator(nameTxt.getText(), "Invalid Name"))
                        .family(Validator.nameValidator(familyTxt.getText(), "Invalid Family"))
                        .gender(Gender.valueOf(gender.getText()))
                        .birthDate(birthDate.getValue())
                        .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                        .algorithmSkill(algoChk.isSelected())
                        .JavaSESkill(seChk.isSelected())
                        .JavaEESkill(eeChk.isSelected())
                        .build();
                PersonBl.getPersonBl().save(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Person Saved\n" + customer);
                alert.show();
                resetForm();
                log.info("Person Saved " + customer);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person Save Error\n" + e.getMessage());
                alert.show();
                log.error("Save Error : " + e.getMessage());
            }
        });

        editBtn.setOnAction(event -> {
            try {
                RadioButton gender = (RadioButton) genderToggle.getSelectedToggle();

                Customer customer = Customer
                        .builder()
                        .id(Integer.parseInt(idTxt.getText()))
                        .name(Validator.nameValidator(nameTxt.getText(), "Invalid Name"))
                        .family(Validator.nameValidator(familyTxt.getText(), "Invalid Family"))
                        .gender(Gender.valueOf(gender.getText()))
                        .birthDate(birthDate.getValue())
                        .city(City.valueOf(cityCmb.getSelectionModel().getSelectedItem()))
                        .algorithmSkill(algoChk.isSelected())
                        .JavaSESkill(seChk.isSelected())
                        .JavaEESkill(eeChk.isSelected())
                        .build();
                PersonBl.getPersonBl().edit(customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Person Edited\n" + customer);
                alert.show();
                resetForm();
                log.info("Person Edited " + customer);

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person Edit Error\n" + e.getMessage());
                alert.show();
                log.error("Edit Error : " + e.getMessage());
            }
        });

        removeBtn.setOnAction(event -> {
            try {
                PersonBl.getPersonBl().remove(Integer.parseInt(idTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, " Person Removed\n" + idTxt.getText());
                alert.show();
                log.info("Person Removed " + idTxt.getText());
                resetForm();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Person Remove Error\n" + e.getMessage());
                alert.show();
                log.error("Remove Error : " + e.getMessage());
            }
        });

        searchFamilyTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(PersonBl.getPersonBl().findByFamily(searchFamilyTxt.getText()));
                log.info("Person Searched By Family : " + searchFamilyTxt.getText());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Persons\n" + e.getMessage());
                alert.show();
                log.error("Find By Family Error : " + e.getMessage());
            }
        });

        personTbl.setOnMouseClicked((event) -> {
            Customer customer = personTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(customer.getId()));
            nameTxt.setText(customer.getName());
            familyTxt.setText(customer.getFamily());
            if (customer.getGender().equals(Gender.Male)) {
                maleRdo.setSelected(true);
            } else {
                femaleRdo.setSelected(true);
            }

            birthDate.setValue(customer.getBirthDate());

            cityCmb.getSelectionModel().select(customer.getCity().ordinal());

            algoChk.setSelected(customer.isAlgorithmSkill());
            seChk.setSelected(customer.isJavaSESkill());
            eeChk.setSelected(customer.isJavaEESkill());
        });
    }

    private void showDataOnTable(List<Customer> customerList) {
        ObservableList<Customer> observableList = FXCollections.observableList(customerList);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("family"));

        personTbl.setItems(observableList);
    }

    private void resetForm() throws Exception {
        idTxt.clear();
        nameTxt.clear();
        familyTxt.clear();
        maleRdo.setSelected(true);
        birthDate.setValue(null);
        cityCmb.getSelectionModel().select(0);
        algoChk.setSelected(false);
        seChk.setSelected(false);
        eeChk.setSelected(false);
        showDataOnTable(PersonBl.getPersonBl().findAll());
    }
}
