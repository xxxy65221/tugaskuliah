/*========================================*
*  Nursyahjaya ramadaniputra              *
*  120202040                              *
*  github.com/nursyah21                   *
*                                         *
*  boleh dicopas!!                        *
*  dengan syarat anda paham dengan apa    *
*  yang anda tulis                        *
*                                         *
*  Program untuk menghitung efek doppler  *
*  fp =  frekuensi pendengar (hz)         *
*  fs =  frekuensi sumber bunyi (hz)      *
*  v  =  kecepatan rambat udara           *
*         (340 m/s umumnya)               *
*  vp =  kecepatan pendengar  (m/s)       *
*       (positif mendekati sumber bunyi)  *
*  vs =  kecepatan sumber bunyi  (m/s)    *
*        (positif menjauhi pendengar)     *
*                                         *
*   rumus:                                *
*         fp/fs = (v +- vp)/(v -+ vs)     *
*=========================================*/

#include <iostream>

class Doppler{
   private:
      int fp, fs; //dalam satuan hz
      int vp, vs, v; //dalam satuan m/s
   public:
      //constructor
      //memasukkan variable
      Doppler(int fp, int fs, int vp, int vs, int v=340){
         this->fp = fp, this->fs = fs;
         this->vp = vp, this->vs = vs;
         this->v = v;
      }
      Doppler(){
         fp = fs = vs = vp = v =0;
      }

      void help(){
         std::cout   <<"/*========================================*\n"
                     <<"*  Nursyahjaya ramadaniputra              *\n"
                     <<"*  120202040                              *\n"
                     <<"*                                         *\n"
                     <<"*                                         *\n"
                     <<"*  Program untuk menghitung efek doppler  *\n"
                     <<"*  fp =  frekuensi pendengar              *\n"
                     <<"*  fs =  frekuensi sumber bunyi           *\n"
                     <<"*  v  =  kecepatan rambat udara           *\n"
                     <<"*         (340 m/s umumnya)               *\n"
                     <<"*  vp =  kecepatan pendengar              *\n"
                     <<"*       (positif mendekati sumber bunyi)  *\n"
                     <<"*                                         *\n"
                     <<"*  vs =  kecepatan sumber bunyi           *\n"
                     <<"*        (positif menjauhi pendengar)     *\n"
                     <<"*                                         *\n"
                     <<"*   rumus:                                *\n"
                     <<"*         fp/fs = (v +- vp)/(v -+ vs)     *\n"
                     <<"*   tekan 0 / q untuk berhenti              *\n"                     
                     <<"*=========================================*/\n";
      }
      void set(){
        std::cout << "\nsilahkan mengisi variable yang anda ketahui\n";
        std::cout << "yang tidak anda tau, silahkan diisi dengan nilai 0\n";
        std::cout << "fp(hz) = "; std:: cin >> fp;
        std::cout << "fs(hz) = "; std:: cin >> fs;
        std::cout << "v(m/s) = "; std:: cin >> v;
        std::cout << "vs(m/s) = "; std:: cin >> vs;
        std::cout << "vp(m/s) = "; std:: cin >> vp;
      }
      //untuk menghindari error, maka kita mereturn 0 jika v = 0
      int hasil_fs(){
         if (v == 0 ) return 0;
         if(fs == 0) fs = ((v + vs)*fp) / (v + vp);
         return fs;
       }
      int hasil_fp(){ 
         if (v == 0) return 0;
         if(fp == 0) fp = ((v + vp)*fs) / (v + vs);
         return fp;
      }
      //mengubah km/j ke m/s
      int kmtom (int km){return (km * 1000)/ 3600; } 
      
      //destructor, untuk sementara tidak melakukan apa-apa
      ~Doppler(){}
};

void menu(){
      std::cout << "menu:\n"
                << "1. set variable\n"
                << "2. hasil fs\n"
                << "3. hasil fp\n"
                << "4. ubah km/j ke m/s\n"
                << "5. help\n"
                << "6. menu\n"
                << "tekan 0 / q untuk berhenti\n";
}

int main(){
   Doppler frekuensiKereta(0,720,10,20);
   frekuensiKereta.help();
   int fp_kereta = frekuensiKereta.hasil_fp();
   
   //test hasil
   std::cout << "\njika fs=720, v=340, vp=10, vs=20. maka fp=700\n";
   std::cout << "frekuensi kereta oleh pendengar adalah: " << fp_kereta << "hz\n\n";
   
   //menu
   //tekan q (quit) / 0 (false), untuk berhenti
   
   Doppler frekuensi;
   char opt;
   menu();
   while (true){
      std::cout << "pilihanmu: "; std::cin >> opt;
      if(opt == 'q' || opt == '0') break;
      
      switch(opt){
         case '1':
            frekuensi.set();
            break;
         case '2':
            std::cout << "fs = " << frekuensi.hasil_fs() << "\n";
            break;
         case '3':
            std::cout << "fp = " << frekuensi.hasil_fp() << "\n";
            break;
         case '4':
            int kmj;
            std::cout << "masukkan nilai (km/j): "; std::cin >> kmj;
            std::cout << "-> (m/s) "<< frekuensi.kmtom(kmj) << "\n";
            break;
         case '5':
            frekuensi.help();
            break;
         case '6':
            menu();
            break;
         default: 
            std::cout << "pilihan " << opt << " tidak tersedia\n";
            break;
      }
   }
   std::cout << "bye-bye\n";
   return 0;
}
