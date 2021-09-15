import { useContext } from "react";
import { UserContext } from "./AuthProvider";
import { defaultHeaders } from "./utils";

const RegisterForm =  ({ setRegisterFormOpen }) => {
  const { setUser } = useContext(UserContext);
  
  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log(`nickname :${event.target.nickname.value}`);
    const res =  await fetch("/users", {
      method: "POST",
      headers: defaultHeaders,
      body: JSON.stringify({
        nickname: event.target.nickname.value,
      }),
    });
    const user = await res.json();
    console.log(`post /users ${JSON.stringify(user)}`);
    setRegisterFormOpen(false);
    setUser(user);
  };

  return (
    <div>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Username:
          <input type="text" name="nickname" />
        </label>
        <input type="submit" value="Register" />
      </form>
    </div>
  );
}

export default RegisterForm;