package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import common.MsgEnum;
import common.ObjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class PerformanceBehindReportPopupController implements Initializable {
	@FXML
    private Label lblMedianNumDelays;

    @FXML
    private Label lblstandarddeviationNumDelays;
    
    @FXML
    private Label lblMedianDelayTime;

    @FXML
    private Label lblstandarddeviationDelayTime;
    
    @FXML
    private ComboBox<String> cmbSystem;
    
    @FXML
    private TableView<FrequencyDistribution> tblFrequencyDistributionnumdelays;
    
    @FXML
    private TableColumn<FrequencyDistribution, Integer> colRangenumdelays;

    @FXML
    private TableColumn<FrequencyDistribution, Integer> colTotalnRangenumdelays;
    
    @FXML
    private TableView<FrequencyDistribution> tblFrequencyDistributiontimedelay;

    @FXML
    private TableColumn<FrequencyDistribution, Integer> colRangetimedelay;

    @FXML
    private TableColumn<FrequencyDistribution, Integer> colTotalnRangetimedelay;
    
    @FXML
    private Button btnData;
    
    @FXML
    private Label lblsytstemerr;
    
    private static Map<String, Integer> daysOfDelay=new HashMap<String, Integer>();
    
    private static Map<String,Integer> timeOfDelay =new HashMap<String, Integer>();
    
    private static ObjectManager generatePerformanceBehindReport;

	public static void setDaysOfDelay(Map<String, Integer> daysOfDelay) {
		PerformanceBehindReportPopupController.daysOfDelay = daysOfDelay;
	}

	public static void setTimeOfDelay(Map<String, Integer> timeOfDelay) {
		PerformanceBehindReportPopupController.timeOfDelay = timeOfDelay;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		generatePerformanceBehindReport= new ObjectManager(daysOfDelay, timeOfDelay, MsgEnum.CREATE_PERFORMANCE_BEHIND_REPORT);
		ConnectionController.getClient().handleMessageFromClientUI(generatePerformanceBehindReport);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		List<String> ITSystemsList =new ArrayList<String>();
		ITSystemsList.add("Braude Website");
		ITSystemsList.add("Info Braude");
		ITSystemsList.add("Library");
		ITSystemsList.add("Moodle");
		ITSystemsList.add("Office");
		ObservableList<String> obList=FXCollections.observableList(ITSystemsList);
		cmbSystem.getItems().clear();
		cmbSystem.setItems(obList);
		lblsytstemerr.setTextFill(Color.RED);
		lblsytstemerr.setVisible(false);
	}
	
	  @FXML
	    void displayData(ActionEvent event) {
		  ArrayList<Integer> numofDelays=new ArrayList<Integer>();
		  ArrayList<Integer> timeofDelays= new ArrayList<Integer>();
		  Integer sumofNumDelays=0,sumofDelayTime=0,countNumDelays=1,countDelayTime=1;
		  //Integer arr[];
		  if(cmbSystem.getSelectionModel().isEmpty())
			  lblsytstemerr.setVisible(true);
		  else {
			  lblsytstemerr.setVisible(false);
			  switch(cmbSystem.getSelectionModel().getSelectedItem()) {
			  case "Braude Website":
				  for(Map.Entry<String, Integer> entry : daysOfDelay.entrySet())
					  if(entry.getKey().equals("Braude Website"))
						  numofDelays.add(entry.getValue());
				  Collections.sort(numofDelays);
					  if(((numofDelays.size())%2)==0)
					  {
						  Integer avg1=(Math.addExact(numofDelays.get(numofDelays.size()/2-1), numofDelays.size()/2)/2);
						  lblMedianNumDelays.setText(avg1.toString());
					  }
					  else
						  lblMedianNumDelays.setText(numofDelays.get(numofDelays.size()/2).toString());
					  for(Integer val : numofDelays)
						  sumofNumDelays+=val;
					  Double standardDiviation=(double)(sumofNumDelays/numofDelays.size());
					  lblstandarddeviationNumDelays.setText(standardDiviation.toString());
					  Integer[] arr=new Integer[numofDelays.size()];
					  int i=0;
					  for(Integer j : numofDelays) {
						  arr[i]=j;
						  i++;
					  }
					  for(i=1; i<arr.length;i++)
					  {
						  if(arr[i-1]==arr[i])
							  countNumDelays++;
						  else
						  {
							  tblFrequencyDistributionnumdelays.getItems().add(new FrequencyDistribution(arr[i-1], countNumDelays));
							  countNumDelays=1;
						  }
					  }
					  for(Map.Entry<String, Integer> entry: timeOfDelay.entrySet())
						  if(entry.getKey().equals("Braude Website"))
							  timeofDelays.add(entry.getValue());
					  Collections.sort(timeofDelays);
					  if(((timeofDelays.size())%2)==0)
					  {
						  Integer avg2=(Math.addExact(timeofDelays.get(timeofDelays.size()/2-1), timeofDelays.size()/2)/2);
						  lblMedianDelayTime.setText(avg2.toString());
					  }
					  else
						  lblMedianDelayTime.setText(timeofDelays.get(timeofDelays.size()/2).toString());
					  for(Integer val : timeofDelays)
						  sumofDelayTime+=val;
					  Double standardDiviation2=(double)(sumofDelayTime/timeofDelays.size());
					  lblstandarddeviationDelayTime.setText(standardDiviation2.toString());
					  arr=new Integer[timeofDelays.size()];
					  arr=(Integer[])timeofDelays.toArray();
					  for(i=1; i<arr.length;i++)
					  {
						  if(arr[i-1]==arr[i])
							  countDelayTime++;
						  else
						  {
							  tblFrequencyDistributiontimedelay.getItems().add(new FrequencyDistribution(arr[i-1], countDelayTime));
							  countDelayTime=1;
						  }
					  }
					  break;
				  }
			  }
		  
		  }
	    }
	  class FrequencyDistribution {
		  private Integer range,totalInRange;
		  public FrequencyDistribution(Integer range,Integer totalInRange) {
			  this.range=range;
			  this.totalInRange=totalInRange;
		  }
}
