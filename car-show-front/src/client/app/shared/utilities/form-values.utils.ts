
export class FormValuesUtils {

    private brands: Array<string> = ['Audi', 'Bmw', 'Citroen', 'Fiat', 'Ford', 'Mercedes', 'Opel', 'Peugoet',
        'Renault', 'Volkswagen'];

    private mode :Map<string, Array<string>> = new Map(
       [[ "Audi", ['A1', 'A2', 'A3', 'A4', 'A5', 'A6', 'A7', 'A8', 'Q3', 'Q5', 'Q7']],
       ["Bmw", ['Serie 1', 'Serie 2', 'Serie 3', 'Serie 4', 'Serie 5', 'Serie 6', 'Serie 7', 'Serie 8','X1', 'X3', 'X4', 'X5', 'X6']],
        ["Citroen", ['C1', 'C2', 'C3', 'C4', 'C5', 'C6', 'C7', 'C8', 'Ds3', 'Ds4', 'Ds5']],
        ["Fiat", ['Punto', 'Palio', 'Panda', 'Regata', 'Idea', 'Brava', 'Bravo', 'Argenta', 'Bertone', 'Doblo', 'Coupe']],
        ["Ford", ['Fiesta', 'Focus', 'Fusion', 'Galaxy', 'Granada', 'Capris', 'C-max', 'Mondeo', 'Puma', 'Prob', 'Sierra']],
        ["Mercedes", ['Class A', 'Class B', 'Class C', 'Class CI', 'Class E', 'CLass G', 'Class M', 'Class R']],
        ["Opel", ['Adam', 'Agila', 'Ampera', 'Antana', 'Ascone', 'Astra', 'Calibra', 'Cascada']],
        ["Peugoet", ['104', '106', '107', '108', '205', '206', '207', '208', '305', '306', '307']],
        ["Renault", ['Alpine', 'Avantime', 'Clio', 'Express', 'Fluence', 'Kangoo', 'Laguna', 'Latitude', 'Master', 'Megane', 'Modus']],
        ["Volkswagen", ['Golf', 'Bora', 'Caddy', 'Combi', 'Corrado', 'Fox', 'Jetta', 'Passat', 'Polo', 'Tiguan', 'Touareg']]]
   )
     
     getBrands() {
         return this.brands;
     }

     getModels(brand:string) {
         return this.mode.get(brand);
     }

     getYears(){
         let years = [];
         let minYear = 1950 ;
         let currentYear :number = new Date().getFullYear()
         for(let i =minYear; i <=currentYear ; i++){
             years.push(i);
         }
         return years;
     }


     getMiles() {
         let miles = [];
         let mileMin = 0;
         let mileMax = 250000;
         for(let i = mileMin ; i<= mileMax; i += 10000){
             miles.push(i);
         }
         return miles ;
     }

}