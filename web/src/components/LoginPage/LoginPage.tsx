import React from "react";
import StyledFirebaseAuth from "../StyledFirebaseAuth/StyledFirebaseAuth";
import {
  browserSessionPersistence,
  getAuth,
  GoogleAuthProvider,
} from "firebase/auth";

interface LoginPageProps {}

function LoginPage() {
  const auth = getAuth();
  auth.setPersistence(browserSessionPersistence);

  // Configure FirebaseUI.
  const uiConfig = {
    // Popup signin flow rather than redirect flow.
    signInFlow: "popup",
    // Redirect to /signedIn after sign in is successful. Alternatively you can provide a callbacks.signInSuccess function.
    signInSuccessUrl: "/",
    // We will display Google and Facebook as auth providers.
    signInOptions: [GoogleAuthProvider.PROVIDER_ID],
    firebaseAuth: auth,
  };
  return (
    <div>
      <h1>My App</h1>
      <p>Please sign-in:</p>
      <StyledFirebaseAuth uiConfig={uiConfig} firebaseAuth={getAuth()} />
    </div>
  );
}

export default LoginPage;
