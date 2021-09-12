import { useContext } from 'react';
import { UserContext } from './AuthProvider';
import { signInGoogle, signOut } from './firebaseAuth';


function App() {
  const { user }  = useContext(UserContext);
  return (
      <div>
        {user && <p>{user.nickName}</p>}
        {user ? (  <button onClick={signOut}>Sign Out</button> ) : ( <button onClick={signInGoogle}>Sign In</button> )}
      </div>
  );
}

export default App;
