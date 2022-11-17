import { apiClient } from './ApiClient'

export const retrieveAllTodosForUsernameApi
    = (username) => apiClient.get(`/users/${username}/todos`)

export const deleteTodoApi
    = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)

export const retrieveTodoApi
    = (username, id) => apiClient.get(`/users/${username}/todos/${id}`)

export const updateTodoApi
    = (username, id, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)

export const createTodoApi
    = (username,  todo) => apiClient.post(`/users/${username}/todos`, todo)
