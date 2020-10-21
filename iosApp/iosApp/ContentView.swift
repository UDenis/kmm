import SwiftUI
import shared

func greet() -> String {
    return Greeting().greeting()
}

struct ContentView: View {
    @ObservedObject var delayedUpdater = DelayedUpdater()
    
    var body: some View {
        return VStack {
            Text(greet())
            Button(action: {
                self.delayedUpdater.caclFactorial()
            }){
                Text("Go")
            }
            Text(delayedUpdater.result)
        }
    }
}

struct ContentView_Previews: PreviewProvider {

    static var previews: some View {
        ContentView()
    }
}
