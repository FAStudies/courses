//
//  ContentView.swift
//  Shared
//
//  Created by Harsh Chaturvedi on 28/05/21.
//

import SwiftUI

struct ContentView: View {
    @State private var sleepAmount = 8.0
    @State private var wakeUp = defaultWakeTime
    @State private var coffeeAmount = 1
    
    @State private var alertTitle = ""
    private var alertMessage: String {
        let model = SleepCalculator()
        let components = Calendar.current.dateComponents([.hour, .minute], from: wakeUp)
        let hour = (components.hour ?? 0) * 60 * 60
        let minute = (components.minute ?? 0) * 60
        do {
            let prediction = try model.prediction(wake: Double(hour+minute), estimatedSleep: sleepAmount, coffee: Double(coffeeAmount))
            let sleepTime = wakeUp - prediction.actualSleep
            let formatter = DateFormatter()
            formatter.timeStyle = .short
            return formatter.string(from: sleepTime)
        } catch {
            return "Sorry, There was a problem calculating your bedtime"
        }
    }
    @State private var showingAlert = false
    
    var body: some View {
        
        NavigationView {
            
            Form {
                
                Section(header: Text("When do you want to wake up?")) {
                    DatePicker("Enter date:", selection: $wakeUp, displayedComponents: .hourAndMinute)
                        .labelsHidden()
                        .datePickerStyle(WheelDatePickerStyle())
                }
                
                Section(header: Text("Desired amount of sleep")) {
                    Stepper(value: $sleepAmount, in: 4...12, step: 0.25) {
                        Text("\(sleepAmount, specifier: "%g") hours")
                    }
                }
                
                
                Section(header: Text("Daily Coffee Intake")) {
                    Stepper(value: $coffeeAmount, in: 1...20) {
                        coffeeAmount==1 ? Text("1 Cup") : Text("\(coffeeAmount) cups")
                    }
                }
                
                Section(header: Text("Recommended Sleep Time")) {
                    Text(alertMessage).font(.largeTitle).padding()
                }
                
            }
            
            .navigationTitle("BetterRest")
//            .navigationBarItems(trailing: Button(action: calculateBedtime) {
//                Text("Calculate")
//            })
            .alert(isPresented: $showingAlert, content: {
                Alert(title: Text(alertTitle), message: Text(alertMessage), dismissButton: .default(Text("OK")))
            })
            
        }
        
    }
    
    static var defaultWakeTime: Date {
        var components = DateComponents()
        components.hour = 7
        components.minute = 0
        return Calendar.current.date(from: components) ?? Date()
    }
    
//    func calculateBedtime() {
//        let model = SleepCalculator()
//        let components = Calendar.current.dateComponents([.hour, .minute], from: wakeUp)
//        let hour = (components.hour ?? 0) * 60 * 60
//        let minute = (components.minute ?? 0) * 60
//        do {
//            let prediction = try model.prediction(wake: Double(hour+minute), estimatedSleep: sleepAmount, coffee: Double(coffeeAmount))
//            let sleepTime = wakeUp - prediction.actualSleep
//            let formatter = DateFormatter()
//            formatter.timeStyle = .short
//            alertMessage = formatter.string(from: sleepTime)
//            alertTitle = "Your Ideal Bedtime is..."
//        } catch {
//            alertTitle = "Error"
//            alertMessage = "Sorry, There was a problem calculating your bedtime"
//        }
//        showingAlert = true
//    }
    
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

// Get duration from DateComponents()
//        var components = DateComponents()
//        components.hour = 8
//        components.minute = 0
//        let date = Calendar.current.date(from: components) ?? Date()

// Get components from Date()
//        let components = Calendar.current.dateComponents([.hour, .minute], from: someDate)
//        let hour = components.hour ?? 0   // Returns Optional
//        let minute = components.minute ?? 0   // Returns Optional

// Format Date to get a string that looks good
//        let formatter=  DateFormatter()
//        formatter.timeStyle = .short
//        let dateString = formatter.string(from: Date())
