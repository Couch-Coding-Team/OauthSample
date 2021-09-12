import { useContext } from 'react';
import { UserContext } from './AuthProvider';
import { signInGoogle, signOut } from './firebaseAuth';


function App() {
  const { user }  = useContext(UserContext);
  return (
      <div>
        {user && <p>{user.nickname} {user.email} </p>}
        {user ? (  <button onClick={signOut}>Sign Out</button> ) : 
          ( <button onClick={signInGoogle}>Sign in With Google</button> )}
      </div>
  );
}

export default App;
