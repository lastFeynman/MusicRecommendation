package Entity;
import java.util.Date;


public class Song {
	private String id;
	private String name;
	private String singer;
	private String album;
	private String url;
	private String cover;
	private String lrc;
	private int style;
	private int scene;
	private int category;
	private Date date;
	public double point;
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSinger(String singer){
		this.singer = singer;
	}
	
	public String getSinger(){
		return singer;
	}
	
	public void setAlbum(String album){
		this.album = album;
	}
	
	public String getAlbum(){
		return album;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setCover(String cover){
		this.cover = cover;
	}
	
	public String getCover(){
		return cover;
	}
	
	public void setLrc(String lrc){
		this.lrc = lrc;
	}
	
	public String getLrc(){
		return lrc;
	}
	
	public void setStyle(int style){
		this.style = style;
	}
	
	public int getStyle(){
		return style;
	}
	
	public void setScene(int scene){
		this.scene = scene;
	}
	
	public int getScene(){
		return scene;
	}
	
	public void setCategory(int category){
		this.category = category;
	}
	
	public int getCategory(){
		return category;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return date;
	}
}
