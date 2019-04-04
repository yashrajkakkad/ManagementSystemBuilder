class Student {
   private int studentID;
   private String name;
   
   public Student(int studentID, String name ) {
      this.studentID = studentID;
      this.name = name;
   }
   
   int getStudentID() {
      return studentID;
   }
   String getName() {
      return name;
   }
   
   void setStudentID(int studentID) {
      this.studentID = studentID;
   }
   void setName(String name) {
      this.name = name;
   }
   
}
