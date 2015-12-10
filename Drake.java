 float RedX, RedY, RedDX, RedDY;
  float YelX, YelY, YelDX, YelDY;
  float BluX, BluY, BluDX, BluDY;
  float ballDiameter;
  
  float mousex, mousey, mousedx, mousedy;
  
  float wallx,wally1,wally2;
  
  float r,g,b;
  
  float left,right, top, bottom;
  float middle= left + (right-left) / 2;
  int score = 0;
  int frameNumber=0;
  
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
   RedX=  random( middle,right );   RedY=  random( top, bottom );
   YelX=  random( middle,right );   YelY=  random( top, bottom );
   BluX=  random( middle,right );   BluY=  random( top, bottom );
   // Random speeds
 
   RedDX=  random( 1,3 );   RedDY=  random( 1,3 );
   YelDX=  random( 1,3 );   YelDY=  random( 1,3 );
   BluDX=  random( 1,3 );  BluDY=  random( 1,3 );
  
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
  RedX += RedDX;  if ( RedX<left || RedX>right )   RedDX *= -1;
  RedY += RedDY;  if ( RedY<top  || RedY>bottom ) RedDY *=  -1;
  YelX += YelDX;  if ( YelX<left || YelX>right )   YelDX *= -1;
  YelY += YelDY;  if ( YelY<top  || YelY>bottom ) YelDY *=  -1;
  BluX += BluDX;  if ( BluX<left || BluX>right )   BluDX *= -1;
  BluY += BluDY;  if ( BluY<top  || BluY>bottom ) BluDY *=  -1;
  
 {RedX += RedDX;  if ( RedX<wallx+15 )   RedDX *= -1;
  YelX += YelDX;  if ( YelX<wallx+15 )   YelDX *= -1;
  BluX += BluDX;  if ( BluX<wallx+15 )   BluDX *= -1;
  }
 }


void collisions() {
  float tmp;          // SwaBlug velocities
  if ( dist( RedX,RedY, YelX,YelY ) < 30 ) {
    tmp=YelDX;  YelDX=RedDX;  RedDX=tmp;
    tmp=YelDY;  YelDY=RedDY;  RedDY=tmp;
  }
  if ( dist( RedX,RedY, BluX,BluY) < 30 ) {
    tmp=BluDX;  BluDX=RedDX;  RedDX=tmp;
    tmp=BluDY;  BluDY=RedDY;  RedDY=tmp;
  }
  if ( dist( BluX,BluY, YelX, YelY) < 30 ) {
    tmp=YelDX;  YelDX=BluDX;  BluDX=tmp;
    tmp=YelDY;  YelDY=BluDY;  BluDY=tmp;
  }
  
}
void show(){       // create balls
  fill( 255,255,255 );    ellipse( RedX,RedY, 30,30 );
  fill( 255,0,0 );    ellipse( RedX,RedY, 30,30 );
  fill( 255,255,0 );  ellipse( YelX,YelY, 30,30 );
  fill( 0,0,255 );    ellipse( BluX,BluY, 30,30 );
  textSize(20);
  fill(0);
  text("1",RedX-6,RedY+5);
  text("2", YelX-6,YelY+5);
  text("3",BluX-6,BluY+5);
  textSize(12);
}

void messages(){        //message placement
  fill(0);
  text( title, 20, 20 );
  text( news, 20, 40 );
  text( author, 50, height-5 );
  text("Press 'M' to start the mouse",450,10);
  text("Press 'P' to change table color to Bluk",450,20);
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
  if(dist(mouseX,mouseY,RedX,RedY) <ballDiameter/2){
   RedX=random(middle,right);
   RedY=random(top,bottom);
 }
 if(dist(mouseX,mouseY,YelX,YelY) <ballDiameter/2){
   YelX=random(middle,right);
   YelY=random(top,bottom);
 }
  if(dist(mouseX,mouseY,BluX,BluY) <ballDiameter/2){
   BluX=random(middle,right);
   BluY=random(top,bottom);
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
    RedX= random(middle+10,right);
    RedY= random(top,bottom);
  }
  if (key == '2'){
    YelX= random(middle+10,right);
    YelY= random(top,bottom);
  }
  if (key == '3'){
    BluX= random(middle+10,right);
    BluY= random(top,bottom);
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
