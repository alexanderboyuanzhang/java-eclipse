package com.bz.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "nameslist")
@XmlAccessorType(XmlAccessType.FIELD)
public class NamesList {

	@XmlElement(name = "names")
	private List<Names> namesList = null;

	public List<Names> getNames() {
		return namesList;
	}

	public void setNames(List<Names> namesList) {
		this.namesList = namesList;
	}
}
