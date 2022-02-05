import axios from 'axios';
import { TaskInitialOptionContext } from './task-initial-option.model';

const baseApiUrl = 'api/informacoes-process/task-initial-option';

export default class TaskInitialOptionService {
  public loadContext(taskId: number): Promise<TaskInitialOptionContext> {
    return new Promise<TaskInitialOptionContext>((resolve, reject) => {
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

  public claim(taskId: number): Promise<TaskInitialOptionContext> {
    return new Promise<TaskInitialOptionContext>((resolve, reject) => {
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

  public complete(taskInitialOptionContext: TaskInitialOptionContext): Promise<any> {
    return new Promise<any>((resolve, reject) => {
      axios
        .post(`${baseApiUrl}/complete`, taskInitialOptionContext)
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });
    });
  }
}
