import axios from 'axios';
import { TaskAgendarContext } from './task-agendar.model';

const baseApiUrl = 'api/informacoes-process/task-agendar';

export default class TaskAgendarService {
  public loadContext(taskId: number): Promise<TaskAgendarContext> {
    return new Promise<TaskAgendarContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public claim(taskId: number): Promise<TaskAgendarContext> {
    return new Promise<TaskAgendarContext>((resolve, reject) => {
      axios
        .get(`${baseApiUrl}/${taskId}/claim`)
        .then(res => {
          resolve(res.data);
        })
        .catch(err => {
          reject(err);
        });
    });
  }

  public complete(taskAgendarContext: TaskAgendarContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskAgendarContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
