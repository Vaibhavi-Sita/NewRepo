import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [userMessage, setUserMessage] = useState('')
  const [replyMessage, setReplyMessage] = useState('')

  const handleSubmit = async (event) => {
    event.preventDefault()
    const response = await fetch(`/ai/generateCode?message=${userMessage}`)
    const message = await response.text()
    setReplyMessage(message)
  }

  return (
    <div>
      <h1>Message Input</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor='userMessage'>Enter your prompt:</label>
        <input
          type='text'
          id='userMessage'
          value={userMessage}
          onChange={(e) => setUserMessage(e.target.value)}
        />
        <button type='submit'>Submit</button>
      </form>
      <p>{replyMessage}</p>
    </div>
  )
}

export default App
