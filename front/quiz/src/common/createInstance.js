import axios from 'axios';
import { setInterceptors } from './interceptors';

function createInstance() {
	const instance = axios.create({
        baseURL: 'http://localhost:8700/api',  
        headers: { 'content-type': 'application/json' }      
      });
	return instance;
}
export const instance = createInstance();

function createInstanceWithAuth() {
	const instance = axios.create({
        baseURL: 'http://localhost:8700/api',        
      });
	return setInterceptors(instance);
}
export const instanceWithAuth = createInstanceWithAuth();