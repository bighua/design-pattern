package pattern.builder;

public class User {

	// final类型的属性
    private final String firstName; // required
    private final String lastName; // required
    private final int age; // optional
    private final String phone; // optional
    private final String address; // optional
    
    /**
     * 私有构造器，只能通过构造器来构造User实例
     * @param builder
     */
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    
    public String getFirstName() {
        return firstName;
    }
     
    public String getLastName() {
        return lastName;
    }
     
    public int getAge() {
        return age;
    }
     
    public String getPhone() {
        return phone;
    }
     
    public String getAddress() {
        return address;
    }
    
    public static User.UserBuilder builder(String firstName, String lastName) {
        return new User.UserBuilder(firstName, lastName);
    }
    
    @Override
    public String toString() {
    	return "this is a person named " + this.firstName + " " + this.lastName + " " + this.age + " years old who lives in " + this.address + " now. please contact him " + this.phone;
    }
    
    /**
     * Builder可以作为参数传递给一个方法
     * @param build
     * @return
     */
    public static User buildFrom(Builder<User> build) {

        return new User((UserBuilder)build);
    }
    
    public static void main(String[] args) {
		User u = User.builder("xiang hua", "zhu").age(28).address("BJ").build();
		System.out.println(u);
	}
    
    public interface Builder<T> {
    	public T build();
    }
     
    public static class UserBuilder implements Builder<User> {
    	
    	// 必须属性设置为final
        private final String firstName;
        private final String lastName;
        private int age;
        private String phone;
        private String address;
         
        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
         
        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }
         
        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }
         
        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }
         
        public User build() {
        	User u = User.buildFrom(this);
        	// 验证参数
        	if (u.getAge() > 100) throw new IllegalArgumentException();
        	return u;
        }
         
    }
}
