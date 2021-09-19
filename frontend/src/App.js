import { useContext } from 'react';
import { UserContext } from './component/AuthProvider';
import { signInGoogle, signOut } from './auth/firebaseAuth';
import './index.css';


function App() {
  const { user }  = useContext(UserContext);
  return (
      <div>
        {user ? ( <div className='main'> <p>{user.nickname} <br/> {user.email} </p> </div> ) : 
          ( <div className='main'> <p>SNS Sample</p> </div> )}
        {user ? (  <button className='signin' onClick={signOut}>Sign Out</button> ) : 
          ( 
          <button className='signin' onClick={signInGoogle}>Sign in With Google</button> )}
      </div>
  );
}

export default App;
