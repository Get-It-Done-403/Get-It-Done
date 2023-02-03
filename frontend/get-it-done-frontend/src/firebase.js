// NOTE: not finished yet, basic login functions implemented

import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import {
    GoogleAuthProvider,
    getAuth,
    signInWithPopUp,
    signInWithEmailAndPassword,
    createUserWithEmailAndPassword,
    sendPasswordResetEmail,
    signOut,
    } from "firebase/auth";
    import {
    getFirestore,
    query,
    getDocs,
    collection,
    where,
    addDoc,
    } from "firebase/firestore";

import { getAuth, onAuthStateChanged } from "firebase/auth";

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
const analytics = getAnalytics(app);
const auth = getAuth(app);
const db = getFirestore(app);


const logInWithEmailAndPassword = async (email, password) => {
    try {
        await signInWithEmailAndPassword(auth, email, password);
    } catch (err) {
        console.error(err);
        alert(err.message);
    }
};

const registerWithEmailAndPassword = async (name, email, password) => {
    try {
        const res = await createUserWithEmailAndPassword(auth, email, password);
        const user = res.user
        await addDoc(collection(db, "users"), {
            uid: user.uid,
            name,
            authProvider: "local",
            email,
        });
        } catch (err) {
        console.error(err);
        alert(err.message);
    }
};

const sendPasswordReset = async (email) => {
    try {
        await sendPasswordResetEmail(auth, email);
        alert("Password reset link sent!");

    } catch (err) {
        console.error(err);
        alert(err.message);
    }
};


// For frontend when passing data to the backend please send back the user uid if possible
// here is an example
onAuthStateChanged(auth, (user) => {
    if (user) {
        const uid = user.uid;
        const stringVersion = String(uid)
    } else {
    }
});

const logout = () => {
    signOut(auth);
};

export {
    auth,
    db,
    logInWithEmailAndPassword,
    registerWithEmailAndPassword,
    sendPasswordReset,
    logout,
};


