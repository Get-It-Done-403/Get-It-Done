// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// import { getAnalytics } from "firebase/analytics";
import { getAuth, GoogleAuthProvider } from "firebase/auth";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCG7M9rwHWYKPvB03II8geGlQZBRxMrdTw",
        authDomain: "get-it-done-7a708.firebaseapp.com",
        databaseURL: "https://get-it-done-7a708-default-rtdb.firebaseio.com",
        projectId: "get-it-done-7a708",
        storageBucket: "get-it-done-7a708.appspot.com",
        messagingSenderId: "798304706961",
        appId: "1:798304706961:web:f2592d4ddd734528317a30",
        measurementId: "G-EM5NELFXRE"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
// const analytics = getAnalytic/s(app);
// Initialize Firebase Authentication and get a reference to the service
const auth = getAuth(app);
const provider = new GoogleAuthProvider()
export {auth, provider}

