 float dogX, dogY, dogDX, dogDY;
  float eggX, eggY, eggDX, eggDY;
  float pinX, pinY, pinDX, pinDY;
  float ballDiameter;
  
  float mousex, mousey, mousedx, mousedy;
  
  float wallx,wally1,wally2;
  
  float r,g,b;
  
  float left,right, top, bottom;
  float middle= left + (right-left) / 2;
  int score = 0;
  int frameNumber=0;
  
  String title=  "CST112 MIDTERM EXAM";
  String news=   "Click any ball to reset it to right half of table.  (r resets all.)";
  String author=  "Drake";
  
void setup() {
  size(700,500);
  left=50;
  right= width-50;
  top=100;
  bottom= height-50;
  middle= left + (right-left) / 2;
  wallx= right/2;
  wally1=top;
  wally2=bottom;
  r= 50;
  g= 205;
  b= 50;
  mousex= 0;
  mousey=bottom;
  mousedx= 0;
  mousedy= 0;
  ballDiameter= 30;
  //
  reset();
}

void reset(){
   dogX=  random( middle,right );   dogY=  random( top, bottom );
   eggX=  random( middle,right );   eggY=  random( top, bottom );
   pinX=  random( middle,right );   pinY=  random( top, bottom );
   // Random speeds
 
   dogDX=  random( 1,3 );   dogDY=  random( 1,3 );
   eggDX=  random( 1,3 );   eggDY=  random( 1,3 );
   pinDX=  random( 1,3 );  pinDY=  random( 1,3 );
  
  wallx = right/2;
  
  r= 50;
  g= 205;
  b= 50;
  
  mousex=10;
  mousedx=0;
  mousedy=0;
 }

void draw(){
  background(250,250,200);
  rectMode(CORNERS);
  table(left,top,right,bottom);
  wall();
  bounce();
  collisions();
  show();
  messages();
  mouse();
  frameNumber= frameNumber+1;
 }
void table(float left, float top, float right, float bottom){
  fill(r,g,b);
  strokeWeight(20);
  stroke(139,69,19);
  rect(left-25, top-25, right+25, bottom+25);
  stroke(0);
  strokeWeight(1);
 }
 
 void wall(){        //make the wall
   strokeWeight(10);
   line(wallx,wally1,wallx,wally2);
   strokeWeight(1);
 }
   
 void bounce(){    //balls bounce off wall
  dogX += dogDX;  if ( dogX<left || dogX>right )   dogDX *= -1;
  dogY += dogDY;  if ( dogY<top  || dogY>bottom ) dogDY *=  -1;
  eggX += eggDX;  if ( eggX<left || eggX>right )   eggDX *= -1;
  eggY += eggDY;  if ( eggY<top  || eggY>bottom ) eggDY *=  -1;
  pinX += pinDX;  if ( pinX<left || pinX>right )   pinDX *= -1;
  pinY += pinDY;  if ( pinY<top  || pinY>bottom ) pinDY *=  -1;
  
 {dogX += dogDX;  if ( dogX<wallx+15 )   dogDX *= -1;
  eggX += eggDX;  if ( eggX<wallx+15 )   eggDX *= -1;
  pinX += pinDX;  if ( pinX<wallx+15 )   pinDX *= -1;
  }
 }


void collisions() {
  float tmp;          // Swaping velocities
  if ( dist( dogX,dogY, eggX,eggY ) < 30 ) {
    tmp=eggDX;  eggDX=dogDX;  dogDX=tmp;
    tmp=eggDY;  eggDY=dogDY;  dogDY=tmp;
  }
  if ( dist( dogX,dogY, pinX,pinY) < 30 ) {
    tmp=pinDX;  pinDX=dogDX;  dogDX=tmp;
    tmp=pinDY;  pinDY=dogDY;  dogDY=tmp;
  }
  if ( dist( pinX,pinY, eggX, eggY) < 30 ) {
    tmp=eggDX;  eggDX=pinDX;  pinDX=tmp;
    tmp=eggDY;  eggDY=pinDY;  pinDY=tmp;
  }
  
}
void show(){       // create balls
  fill( 255,255,255 );    ellipse( dogX,dogY, 30,30 );
  fill( 255,0,0 );    ellipse( dogX,dogY, 30,30 );
  fill( 255,255,0 );  ellipse( eggX,eggY, 30,30 );
  fill( 0,0,255 );    ellipse( pinX,pinY, 30,30 );
  textSize(20);
  fill(0);
  text("1",dogX-6,dogY+5);
  text("2", eggX-6,eggY+5);
  text("3",pinX-6,pinY+5);
  textSize(12);
}

void messages(){        //message placement
  fill(0);
  text( title, 20, 20 );
  text( news, 20, 40 );
  text( author, 50, height-5 );
  text("Press 'M' to start the mouse",450,10);
  text("Press 'P' to change table color to pink",450,20);
  text("Press 'W' to remove the wall",450,30);
}

void mouse(){

  if( frameNumber/30 % 2 > 0) {   /// Move legs
  line(mousex-20,mousey+8,mousex-15,mousey+20);//leg 1
  line(mousex-15,mousey+8,mousex-10,mousey+20);//leg 2
  line(mousex+10,mousey+8,mousex+ 5,mousey+20);//leg 3
  line(mousex+ 5,mousey+8,mousex- 5,mousey+20);//leg 4
  
  }else{
  line(mousex-20,mousey+8,mousex-25,mousey+20);//leg 1
  line(mousex-15,mousey+8,mousex-20,mousey+20);//leg 2
  line(mousex+10,mousey+8,mousex- 15,mousey+20);//leg 3
  line(mousex+ 5,mousey+8,mousex   ,mousey+20);//leg 4
  }
  if( mousex>width){
    mousex = -10;
  }


  mousex= mousex+ mousedx;
  mousey= mousey+ mousedy;
  ellipse(mousex,mousey,50,25);
  ellipse(mousex+25,mousey-10,20,15);
}

void mousePressed(){
  if(dist(mouseX,mouseY,dogX,dogY) <ballDiameter/2){
   dogX=random(middle,right);
   dogY=random(top,bottom);
 }
 if(dist(mouseX,mouseY,eggX,eggY) <ballDiameter/2){
   eggX=random(middle,right);
   eggY=random(top,bottom);
 }
  if(dist(mouseX,mouseY,pinX,pinY) <ballDiameter/2){
   pinX=random(middle,right);
   pinY=random(top,bottom);
 }
 
}
void keyPressed(){
  if (key == 'r'){  // reset all
    reset();
  }
  if (key == 'r'){
    strokeWeight(10);
   line(wallx,wally1,wallx,wally2);
   strokeWeight(1);
  }
  if (key == '1'){
    dogX= random(middle+10,right);
    dogY= random(top,bottom);
  }
  if (key == '2'){
    eggX= random(middle+10,right);
    eggY= random(top,bottom);
  }
  if (key == '3'){
    pinX= random(middle+10,right);
    pinY= random(top,bottom);
  }
  if (key == 'w') {  // wall goes out of way
    wallx=-100;
  }
  if (key == 'p') {
    r= 255;
    g= 105;
    b= 180;
  } 
  if (key == 'm'){
    mousedx=3;
  }
}
