class ChocoNutty {
   private String Taste;
   private String Mood;
   
   public ChocoNutty(String Taste, String Mood ) {
      this.Taste = Taste;
      this.Mood = Mood;
   }
   
   String getTaste() {
      return Taste;
   }
   String getMood() {
      return Mood;
   }
   
   void setTaste(String Taste) {
      this.Taste = Taste;
   }
   void setMood(String Mood) {
      this.Mood = Mood;
   }
   
}
