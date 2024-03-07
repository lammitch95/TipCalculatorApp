package com.example.tipcalculatorapp;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;


public class TipCalculatorController {

    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentage = new BigDecimal(0);


    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;



    public void initialize(){

        currency.setRoundingMode(RoundingMode.HALF_UP);

        tipPercentageSlider.valueProperty().addListener((observableValue, number, t1) -> {
            tipPercentage = BigDecimal.valueOf(t1.intValue() / 100.0);
            tipPercentageLabel.setText(percent.format(tipPercentage));
            tipCalculations();
        });
        amountTextField.textProperty().addListener((observableValue, s, t1) -> tipCalculations());
    }


    private void tipCalculations(){
        if(!amountTextField.getText().isEmpty()){
            try{
                BigDecimal amount = new BigDecimal(amountTextField.getText());
                BigDecimal tip = amount.multiply(tipPercentage);
                BigDecimal total = amount.add(tip);

                tipTextField.setText(currency.format(tip));
                totalTextField.setText(currency.format(total));
            }catch(NumberFormatException ex){
                amountTextField.setText("Enter amount");
                amountTextField.selectAll();
                amountTextField.requestFocus();

            }
        }
    }
}
