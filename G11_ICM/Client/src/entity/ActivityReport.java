package entity;

import java.util.ArrayList;
import java.util.List;

public class ActivityReport {
	private String numRequests;
	private String numRejectedRequests;
	private String numWorkDaysInvestedInTreatment;
	private String median;
	private String standardDeviation;
	private String frequencyDistribution;
	
	public String getNumRequests() {
		return numRequests;
	}
	public void setNumRequests(String numRequests) {
		this.numRequests = numRequests;
	}
	public String getNumRejectedRequests() {
		return numRejectedRequests;
	}
	public void setNumRejectedRequests(String numRejectedRequests) {
		this.numRejectedRequests = numRejectedRequests;
	}
	public String getNumWorkDaysInvestedInTreatment() {
		return numWorkDaysInvestedInTreatment;
	}
	public void setNumWorkDaysInvestedInTreatment(String numWorkDaysInvestedInTreatment) {
		this.numWorkDaysInvestedInTreatment = numWorkDaysInvestedInTreatment;
	}
	public String getMedian() {
		return median;
	}
	public void setMedian(String median) {
		this.median = median;
	}
	public String getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(String standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public String getFrequencyDistribution() {
		return frequencyDistribution;
	}
	public void setFrequencyDistribution(String frequencyDistribution) {
		this.frequencyDistribution = frequencyDistribution;
	}
	public List<String> getAll(){
		List<String> list = new ArrayList<>();
		list.add(numRequests);
		list.add(numRejectedRequests);
		list.add(numWorkDaysInvestedInTreatment);
		list.add(median);
		list.add(standardDeviation);
		list.add(frequencyDistribution);
		return list;
	}
}
