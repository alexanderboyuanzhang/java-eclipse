# XML schema Definition

### `<Schema>` Element

Schema is the root element of XSD and it is always required. <br>
`<:schema xmlns:xs = "<http://www.w3.org/2001/XMLSchema">` <br>
The above fragment specifies the elements and datatypes use in the schema are defined in `<http://www.w3.org/2001/XMLSchema>` namespace and these elements/data types should be prefixed with **xs**. It is always required. <br>
`targetNamespace = "http://www.tutorialspoint.com"`<br>
The above fragment specifies the elements adn datatypes used in the schema are defined in `http://www.tutorialspoint` namespace. It is optional. <br>
`xmlns = "http://www.tutorialspoint.com"` <br>
The above fragment specifies that default namespace is `http://tutorialspoint.com` <br>
`elementFormDefault` = "qualified" <br>
The above fragment indicates that any element declared in this schema must  be namespace qualified before using them in any XML Document. It is optional.

### Referencing Schema

`xmlns = "http://www.tutorialspoint.com"` <br>
The above fragment specifies default namespace declaration. This namespace is used by the schema validator check that all the elements are part of this namespace. It is optional. <br>
`xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"` <br>
`xsi:schemaLocation = "http://www.tutorialspoint.com student.xsd"` <br>
After defining the XMLSchema-instance xsi, use **schemaLocation** attribute. This attribute has two values, namespace and location of XML Schema, to be used separated by a space. It is optional.

### Validation using a Java class

`javac XSDValidator.java` <br>
`java XSDValidator students.xsd students.xml` <br>

## XSD - Element

Simple Element is an XML element which can only have text. It can not contain any attribute.

### Syntax

`<xs:element name = "element-name" type = "element-type"/>`

|              |                                                                                                                                                                                                                          |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| element-name | Name of the XML Element. For example <br> `<xs:element name = "firstname" type = "xs:string"`<br> defines following element <br> `<firstname></firstname>`                                                               |
| element-type | Type of the XML Element. For example, <br> `<xs:element name = "firstname" type = "xs:string"/>`<br> defines type of element as String, firstname should have value of type string. <br> `<firstname>Boyuan</firstname>` |

### Example

consider the following XML Elements

`<name>Dinkar</name>`<br>
`<marks>90</marks>`<br>
`<birthdate>1985-05-23</birthdate>`<br>

XSD declarations for above XML elements will be as follows −

`<xs:element name = "name" type = "xs:string"/>`<br>
`<xs:element name = "marks" type = "xs:integer"/>`<br>
`<xs:element name = "birthdate" type = "xs:date"/>`<br>

### Default Value

A Simple Element can have a default value assigned. Default values are used in case an element does not have any text.

`<xs:element name = "grade" type = "xs:string" default = "NA" />`

### Default Value

A Simple Element can have a default value assigned. Default values are used in case an element does not have any text.

`<xs:element name = "grade" type = "xs:string" default = "NA" />`

### Fixed Value

Simple Element can have fix value assigned. In case, fixed value is assigned element can not have any text.

`<xs:element name = "class" type = "xs:string" fixed = "1" />`

#### References

<https://www.tutorialspoint.com/xsd/xsd_syntax.htm>
