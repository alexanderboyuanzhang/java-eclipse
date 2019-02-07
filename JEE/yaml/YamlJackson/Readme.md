# YAML 

	We need Jackason databind and Jackson’s YAML extension. These dependencies should get our demo code working:
	
	<dependencies>
	    <dependency>
	        <groupId>com.fasterxml.jackson.dataformat</groupId>
	        <artifactId>jackson-dataformat-yaml</artifactId>
	        <version>2.3.0</version>
	    </dependency>
	    <dependency>
	        <groupId>com.fasterxml.jackson.core</groupId>
	        <artifactId>jackson-databind</artifactId>
	        <version>2.2.3</version>
	    </dependency>
	    <dependency>
	        <groupId>org.apache.commons</groupId>
	        <artifactId>commons-lang3</artifactId>
	        <version>3.4</version>
	    </dependency>
	</dependencies>

## Reading YAML Files to Java Objects

	Let’s consider this sample YAML file for our demo:
	
	# Details of a user
	---
	name: Test User
	age: 30
	address:
	  line1: My Address Line 1
	  line2: Address line 2
	  city: Washington D.C.
	  zip: 20000
	roles: 
	  - User
	  - Editor


	Let’s create a User.java Bean to hold our YAML data:

	import java.util.Map;
	public class User {
	    private String name;
	    private int age;
	    private Map<String, String> address;
	    private String[] roles;
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public int getAge() {
	        return age;
	    }
	    public void setAge(int age) {
	        this.age = age;
	    }
	    public Map<String, String> getAddress() {
	        return address;
	    }
	    public void setAddress(Map<String, String> address) {
	        this.address = address;
	    }
	    public String[] getRoles() {
	        return roles;
	    }
	    public void setRoles(String[] roles) {
	        this.roles = roles;
	    }
	}


## Jackson at Work
	
	Now, here is our code using Jackson’s YamlFactory to read YAML into User bean:

	import java.io.File;
	import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
	import org.apache.commons.lang3.builder.ToStringStyle;
	import com.fasterxml.jackson.databind.ObjectMapper;
	import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
	public class YamlTesting {
	    public static void main(String[] args) {
	        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	        try {
	            User user = mapper.readValue(new File("user.yaml"), User.class);
	            					System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	}



	ReflectionToStringBuilder is just a util class to print a Java Object to a String. Here is the output of this program:

	com.user.bean.User@224edc67[
	  name=Test User
	  age=30
	  address={line1=My Address Line 1, line2=Address line 2, city=Washington D.C., zip=20000}
	  roles={User,Editor}
	]

	
	YamlFactory has taken care of mapping address into MAP and roles into a String array. We can also create an Address.java bean class to hold address data and use it instead of MAP in the User bean. YamlFactory will take care of creating the address object and populate it with values from YAML.
	
### source: https://dzone.com/articles/read-yaml-in-java-with-jackson


