package net.helix.pvp.scoreboard;




import java.util.ArrayList;
import java.util.List;

public class WaveAnimation {
  private String string;
  
  private List<String> strings;
  
  private int index;
  
  private boolean bool;
  
  public WaveAnimation(String text, String c1, String c2, String c3) {
    this(text, c1, c2, c3, 12);
  }
  
  public WaveAnimation(String text, String c1, String c2, String c3, int p) {
    this.string = text;
    this.strings = new ArrayList<>();
    createFrames(c1, c2, c3, p);
  }
  
  private void createFrames(String c1, String c2, String c3, int p) {
    if (this.string != null && !this.string.isEmpty()) {
      int i;
      for (i = 0; i < this.string.length(); i++) {
        if (this.string.charAt(i) != ' ')
          this.strings.add(c1 + this.string.substring(0, i) + c2 + this.string.charAt(i) + c3 + this.string.substring(i + 1)); 
      } 
      for (i = 0; i < p; i++)
        this.strings.add(c1 + this.string); 
      for (i = 0; i < this.string.length(); i++) {
        if (this.string.charAt(i) != ' ')
          this.strings.add(c3 + this.string.substring(0, i) + c2 + this.string.charAt(i) + c1 + this.string.substring(i + 1)); 
      } 
      for (i = 0; i < p; i++)
        this.strings.add(c3 + this.string); 
    } 
  }
  
  public String next() {
    if (this.strings.isEmpty())
      return ""; 
    if (this.bool) {
      this.index--;
      if (this.index <= 0)
        this.bool = false; 
    } else {
      this.index++;
      if (this.index >= this.strings.size()) {
        this.bool = true;
        return next();
      } 
    } 
    return this.strings.get(this.index);
  }
}

